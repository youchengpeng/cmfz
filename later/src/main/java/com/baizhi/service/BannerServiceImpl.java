package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> queryAllByPage(RowBounds rowBounds, Banner banner) {
        List<Banner> list = bannerMapper.selectByRowBounds(banner, rowBounds);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int queryBannerCount(Banner banner) {
        int count = bannerMapper.selectCount(banner);
        return count;
    }

    @Override
    public void updateBanner(Banner banner) {
        int i = bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void addBanner(Banner banner) {
        bannerMapper.insertSelective(banner);
    }

    @Override
    public void deleteBanner(Banner banner) {
        bannerMapper.delete(banner);
    }


}
