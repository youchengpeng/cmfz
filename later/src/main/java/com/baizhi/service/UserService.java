package com.baizhi.service;

import com.baizhi.DTO.UserChartsDto;

import java.util.List;

public interface UserService {
    public List<UserChartsDto> queryProvinceAndConut(int sex);
}
