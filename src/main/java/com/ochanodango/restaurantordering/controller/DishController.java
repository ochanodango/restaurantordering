package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Dish;
import com.ochanodango.restaurantordering.service.CategroyService;
import com.ochanodango.restaurantordering.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategroyService categroyService;
    @GetMapping("/list")
    public R list(){
        List<Dish> list = dishService.list();
        return R.success(list, "所有菜品列表");
    }

    @GetMapping("/categoryId")
    public R listByCategoryId(@RequestParam("categoryId") Integer categoryId){
        if(categroyService.getById(categoryId) != null){
            List<Dish> list = dishService.selectByCategoryId(categoryId);
            return R.success(list, "菜品列表分类获取");
        }
        return R.fail("菜品列表分类获取失败");
    }


}
