package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Orders;
import com.ochanodango.restaurantordering.mapper.DishMapper;
import com.ochanodango.restaurantordering.mapper.OrderMapper;
import com.ochanodango.restaurantordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DishMapper dishMapper;

    public List<Orders> selectByStatus(Integer tableId, Integer status){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id", tableId);
        wrapper.eq("status", status);
        List<Orders> list = orderMapper.selectList(wrapper);
        return list;
    }

    public Double totalPrice(Map<String, String> map){
        Double totalPrice = 0.0;
        for (Map.Entry<String, String> entry : map.entrySet()){
            Integer mapKey = Integer.parseInt(entry.getKey());
            Integer mapValue = Integer.parseInt(entry.getValue());
            Double price = dishMapper.selectById(mapKey).getPrice();
            totalPrice = price * mapValue + totalPrice;
        }
        return totalPrice;
    }

    public Orders noPayOrder(Integer tableId){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id", tableId);
        wrapper.eq("status", 0);
        Orders orders = orderMapper.selectOne(wrapper);
        return orders;
    }

    public Orders selectByTableId(Integer tableId){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id", tableId);
        return orderMapper.selectOne(wrapper);
    }
}
