package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BannerService {
    public List<Banner> queryAllByPage(RowBounds rowBounds, Banner banner);

    public int queryBannerCount(Banner banner);

    public void updateBanner(Banner banner);

    public void addBanner(Banner banner);

    public void deleteBanner(Banner banner);
}
