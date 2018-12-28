package com.baizhi.controller;

import com.baizhi.DTO.BannerPageDTO;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerMapper bannerMapper;

    @RequestMapping("/showAllBanner")
    public BannerPageDTO showAllBanner(int rows, int page) {
        // System.out.println(rows+"------"+page);
        BannerPageDTO dto = new BannerPageDTO();

        Banner banner = new Banner();
        int index = (page - 1) * rows;
        RowBounds rowBounds = new RowBounds(index, rows);
        List<Banner> list = bannerService.queryAllByPage(rowBounds, banner);
        int i = bannerService.queryBannerCount(banner);

        dto.setRows(list);
        dto.setTotal(i);
        return dto;
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner) {
        System.out.println(banner);
        bannerService.updateBanner(banner);
    }

    @RequestMapping("/deleteBanner")
    public void deleteBanner(Banner banner, HttpSession session) {
        //删除文件时需要文件名，所以先查出一个来
        Banner one = bannerMapper.selectByPrimaryKey(banner);
        //得到项目下的文件夹路径
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/upload");
        //System.out.println(banner);
        //数据库删除
        bannerService.deleteBanner(banner);
        //System.out.println(one);
        //处理删除时的路径  拼接文件名和文件夹路径
        String path = one.getImg_path();
        String[] split = path.split("/");
        String s = realPath + "/" + split[1];
        // System.out.println(s);
        //删除文件
        deleteFile(s);
    }

    @RequestMapping("/addBanner")
    public void addBanner(HttpSession session, MultipartFile file1, Banner banner) throws IOException {
        //通过ctx得到一个项目下的文件夹路径
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/upload");
        System.out.println(realPath);
        //获得上传的图片的文件名
        String filename = file1.getOriginalFilename();
        String path = realPath + "/" + filename;
        System.out.println(path);
        //通过文件流 上传文件
        File file = new File(path);
        file1.transferTo(file);
        //由于文件名的路径是绝对路径，所以需要自定义写入数据库的文件名
        String dbpath = "upload/" + filename;
        //补全Banner的属性
        Date date = new Date();
        banner.setImg_path(dbpath);
        banner.setPub_date(date);
        // System.out.println(banner);
        //调用service
        bannerService.addBanner(banner);
    }

    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
