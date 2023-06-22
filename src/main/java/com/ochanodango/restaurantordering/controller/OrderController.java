package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Orders;
import com.ochanodango.restaurantordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public R list(){
        List<Orders> list = orderService.list();
        return R.success(list, "全部订单查询");
    }

    @GetMapping("/tableId")
    public R tableOrder(@RequestParam("tableId") Integer tableId){
        List<Orders> list = orderService.selectByStatus(tableId, 0);
        return R.success(list);
    }

    @PostMapping("/add")
    public R addOrder(@RequestBody Map<String, String> map){
        return R.success();
    }

}
