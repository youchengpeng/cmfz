package com.baizhi.service;

import com.baizhi.DTO.UserChartsDto;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserChartsDto> queryProvinceAndConut(int sex) {
        List<UserChartsDto> list = userMapper.queryUserGBPandSex(sex);
        return list;
    }
}
