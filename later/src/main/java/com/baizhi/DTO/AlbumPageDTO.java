package com.baizhi.DTO;

import com.baizhi.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumPageDTO {
    private List<Album> rows;
    ;
    private Integer total;
}
