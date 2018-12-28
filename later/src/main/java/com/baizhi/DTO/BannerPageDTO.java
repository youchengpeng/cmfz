package com.baizhi.DTO;

import com.baizhi.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerPageDTO {
    private List<Banner> rows;
    ;
    private Integer total;
}
