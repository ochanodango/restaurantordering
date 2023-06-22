package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Dish;
import com.ochanodango.restaurantordering.service.CategroyService;
import com.ochanodango.restaurantordering.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/select/dishName")
    public R listByDishName(@RequestParam("dishName") String dishName){
        List<Dish> list = dishService.selectByDishName(dishName);
        return R.success(list, "查询菜品成功");
    }

    @GetMapping("/select/status")
    public R listByStatus(@RequestParam("status") Integer status){
        if(status != 0 || status != 1 ){
            return R.fail("菜品状态查询失败");
        }
        List<Dish> list = dishService.selectByStatus(status);
        return R.success(list,"菜品状态查询成功");
    }

    @PostMapping("/add")
    public R add(@RequestBody Dish dish){
        if(dishService.existDishName(dish.getDishName())){
            dishService.save(dish);
            return R.success("添加菜品");
        }
        return R.fail("添加菜品失败");
    }

}
