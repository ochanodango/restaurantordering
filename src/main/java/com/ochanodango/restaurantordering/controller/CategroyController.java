package com.ochanodango.restaurantordering.controller;


import com.ochanodango.restaurantordering.entity.Categroy;
import com.ochanodango.restaurantordering.mapper.CategroyMapper;
import com.ochanodango.restaurantordering.service.CategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategroyController {
    @Autowired
    CategroyService categroyService;

    @GetMapping("/")
    public List<Categroy> Test(){
        return categroyService.list();
    }
}
