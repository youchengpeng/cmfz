package com.baizhi.controller;

import com.baizhi.DTO.UserChartsDto;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showAllPro")
    public List<UserChartsDto> showAllPro(int sex) {
        List<UserChartsDto> list = userService.queryProvinceAndConut(sex);
        return list;
    }
}
