package com.baizhi.service;

import com.baizhi.DTO.AlbumPageDTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.List;

public interface AlbumService {
    public AlbumPageDTO queryAllAlbumByPage(int page, int rows);

    public void addAlbum(Album album);

    public List<Album> queryAll();

    public void addChapter(Chapter chapter);
}
