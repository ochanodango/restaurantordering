package com.ochanodango.restaurantordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochanodango.restaurantordering.entity.Orders;

import java.util.List;

public interface OrderService extends IService<Orders> {
    List<Orders> selectByStatus(Integer tableId, Integer status);
}
