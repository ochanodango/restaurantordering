package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Orders;
import com.ochanodango.restaurantordering.mapper.OrderMapper;
import com.ochanodango.restaurantordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Orders> selectByStatus(Integer tableId, Integer status){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("tableId", tableId);
        wrapper.eq("status", status);
        List<Orders> list = orderMapper.selectList(wrapper);
        return list;
    }
}
