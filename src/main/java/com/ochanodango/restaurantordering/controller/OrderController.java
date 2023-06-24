package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Orders;
import com.ochanodango.restaurantordering.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/tableIdd")
    public R tableOrder(@RequestParam("tableId") Integer tableId){
        List<Orders> list = orderService.selectByStatus(tableId, 0);
        return R.success(list);
    }

    @GetMapping("/tabalId")
    public R tableOrderById(@RequestParam("table_id") Integer tableId){
        List<Orders> list = orderService.selectByStatus(tableId, 0);
        Map<String, List<JSONObject>> map = new HashMap<>();
        ArrayList<JSONObject> jlist = new ArrayList<>();
        list.forEach(item ->{
            Integer orderid = item.getOrderId();

        });
    }

    @PostMapping("/add")
    public R addOrder(@RequestBody Map<String, String> map){
        Integer tableId = Integer.parseInt(map.get("tableId"));
        map.remove("tableId");
        JSONObject jsonObject = JSONObject.fromObject(map);
        String detail = jsonObject.toString();
        System.out.println(detail);
        Double price = orderService.totalPrice(map);
        if (orderService.noPayOrder(tableId) != null){
            Orders orders = orderService.noPayOrder(tableId);
            Double oldPrice = orders.getTotalPrice();
            orders.setTotalPrice(oldPrice + price);
            orderService.saveOrUpdate(orders);
        }else {
            Orders orders = new Orders();
            orders.setTableId(tableId);
            orders.setTotalPrice(price);
            orders.setDetail(detail);
            orderService.save(orders);
            orders.toString();
        }

        return R.success();
    }

    @GetMapping("/addd")
    public R addd(@RequestParam("table_id") Integer tableId, @RequestParam("content") List<Object> content){
        System.out.println(tableId);
        System.out.println(content.toString());
        return R.success();
    }

    @GetMapping("/pay")
    public R confirmPay(@RequestParam("tableId") Integer tableId) {
        Orders orders = orderService.selectByTableId(tableId);
        orders.setStatus(1);
        orderService.saveOrUpdate(orders);
        return R.success("支付成功");
    }



}
