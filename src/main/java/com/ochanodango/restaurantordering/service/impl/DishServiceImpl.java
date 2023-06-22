package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Dish;
import com.ochanodango.restaurantordering.mapper.DishMapper;
import com.ochanodango.restaurantordering.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishMapper dishMapper;

    public List<Dish> selectByCategoryId(Integer categoryId){
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("categoryId", categoryId);
        List<Dish> list = dishMapper.selectList(wrapper);
        return list;
    }

    public List<Dish> selectByDishName(String dishName){
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.like("dishName", dishName);
        List<Dish> list = dishMapper.selectList(wrapper);
        return list;
    }

    public List<Dish> selectByStatus(Integer status){
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("status",status);
        List<Dish> list = dishMapper.selectList(wrapper);
        return list;
    }

    public Boolean existDishName(String dishName){
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("dishName", dishName);
        if(dishMapper.selectOne(wrapper) != null){
            return true;
        }
        return false;
    }
}
