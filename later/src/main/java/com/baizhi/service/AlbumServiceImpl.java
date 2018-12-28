package com.baizhi.service;

import com.baizhi.DTO.AlbumPageDTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public AlbumPageDTO queryAllAlbumByPage(int page, int rows) {
        AlbumPageDTO dto = new AlbumPageDTO();
        int start = (page - 1) * rows;
        List<Album> list = albumMapper.queryAllByPage(start, rows);
        int count = albumMapper.selectCount(new Album());
        dto.setRows(list);
        dto.setTotal(count);
        return dto;
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.insertSelective(album);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> queryAll() {
        List<Album> list = albumMapper.selectAll();
        return list;
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterMapper.insert(chapter);
    }
}
