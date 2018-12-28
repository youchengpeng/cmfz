package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.DTO.AlbumPageDTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import com.baizhi.util.Audioutil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumMapper albumMapper;


    @RequestMapping("/showAllAlbumByPage")
    public AlbumPageDTO showAllAlbumByPage(int page, int rows) {
        AlbumPageDTO dto = albumService.queryAllAlbumByPage(page, rows);
        return dto;
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(HttpSession session, MultipartFile file1, Album album) {

        //获取当前文件的保存路径+coverimg目录
        String realPath = session.getServletContext().getRealPath("/");
        String dir = realPath + "coverimg";
        File file = new File(dir);
        //没有就创建
        if (!file.exists()) {
            file.mkdir();
        }
        //重命名   测试.mp3
        //先获得了一个文件的真是文件名比如：郝云 - 活着.mp3
        String originalFilename = file1.getOriginalFilename();
        System.out.println(originalFilename);
        //得到文件的后缀名不包括.   如mp3
        String extension = FilenameUtils.getExtension(originalFilename);
        //通过uuid唯一区别存库的文件名和上传路径
        String newName = UUID.randomUUID().toString();
        newName = newName + "." + extension;
        //上传
        try {
            file1.transferTo(new File(dir, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获得文件的音频文件的时长
        Long duration = Audioutil.getDuration(new File(dir + "/" + newName));
        //System.out.println("aaaa");
        // 保存数据到数据库
        //  名字  大小  时长  url  专辑id  上传时间
        album.setBrief(originalFilename);
        album.setPub_date(new Date());
        album.setCover_img(newName);
        albumService.addAlbum(album);
    }

    @RequestMapping("/queryAll")
    public List<Album> queryAll() {
        List<Album> albums = albumService.queryAll();
        return albums;
    }

    @RequestMapping("/addChapter")
    public void addChapter(HttpSession session, MultipartFile file1, Chapter chapter) {
        //System.out.println("------"+file1);
        // System.out.println("======="+chapter);
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "").substring(0, 8);
        chapter.setId(str);

        long size = file1.getSize();
        double v = size / (1024 * 1024.0);
        //获取当前文件的保存路径
        String realPath = session.getServletContext().getRealPath("/");
        String dir = realPath + "audio";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
        //重命名   测试.mp3
        String originalFilename = file1.getOriginalFilename();
        System.out.println(originalFilename);
        //mp3
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = UUID.randomUUID().toString();
        newName = newName + "." + extension;
        //上传
        try {
            file1.transferTo(new File(dir, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = dir + "/" + newName;
        Long duration = Audioutil.getDuration(new File(url));
        chapter.setSize((int) v);
        chapter.setDuration(duration.toString());
        chapter.setUpload_date(new Date());
        chapter.setUrl(newName);
        albumService.addChapter(chapter);
    }

    @RequestMapping("down")
    public void down(String url, String title, HttpSession session, HttpServletResponse response) {
        System.out.println("1111111111111111");
        String realPath = session.getServletContext().getRealPath("/audio");
        String filePath = realPath + "/" + url;
        System.out.println(filePath);
        File file = new File(filePath);
        //获取后缀名
        String extension = FilenameUtils.getExtension(url);
        String oldName = title + "." + extension;
        System.out.println(oldName);
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        response.setContentType("audio/mpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/exportAllAlbum")
    public void exportAllAlbum() {

        List<Album> albums = albumMapper.queryAll();
        for (Album album : albums) {
            album.setCover_img("../coverimg/" + album.getCover_img());
            System.out.println(album.getCover_img());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑详细信息导出", "专辑详情"),
                Album.class, albums);

        try {
            workbook.write(new FileOutputStream(new File("F:/easypoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importAlbum() {
    }

}


