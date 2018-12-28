package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    public List<Album> queryAllByPage(@Param(value = "start") int start, @Param(value = "rows") int rows);

    public List<Album> queryAll();
}
