package com.ochanodango.restaurantordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochanodango.restaurantordering.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Orders> {
    List<Orders> selectByStatus(Integer tableId, Integer status);

    Orders selectByTableId(Integer tableId);

    Double totalPrice(Map<String, String> map);

    Orders noPayOrder(Integer tableId);
}
