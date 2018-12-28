package com.baizhi.mapper;

import com.baizhi.DTO.UserChartsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<UserChartsDto> queryUserGBPandSex(@Param(value = "sex") int sex);
}
