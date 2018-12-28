package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/showAllMenu")
    public List<Menu> showAllMenu() {
        List<Menu> list = menuService.queryAll();
//        for (Menu menu : list) {
//            System.out.println(menu);
//        }
        return list;
    }
}
