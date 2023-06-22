package com.ochanodango.restaurantordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochanodango.restaurantordering.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    List<Dish> selectByCategoryId(Integer categoryId);

    Boolean existDishName(String dishName);

    Integer selectDishName(String dishName);

    List<Dish> selectByDishName(String dishName);

    List<Dish> selectByStatus(Integer status);
}
