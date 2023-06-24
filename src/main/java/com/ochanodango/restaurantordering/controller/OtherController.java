package com.ochanodango.restaurantordering.controller;

import com.alibaba.fastjson.JSONArray;
import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.*;
import com.ochanodango.restaurantordering.service.CategroyService;
import com.ochanodango.restaurantordering.service.DishService;
import com.ochanodango.restaurantordering.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class OtherController {
    @Autowired
    private DishService dishService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private CategroyService categroyService;

    @GetMapping("/food/list")
    public R list(){
        List<Categroy> categroys = categroyService.list();
        ArrayList<JSONObject> data = new ArrayList();

        categroys.forEach(categroy -> {
            JSONObject ca = new JSONObject();
            String type = categroy.getType();
            Integer categroyId = categroy.getCategroyId();

            ArrayList<JSONObject> foods = new ArrayList<>();
            List<Dish> dishList = dishService.selectByCategoryId(categroyId);
            dishList.forEach(dish -> {
                String imageUrl = dish.getImageUrl();
                Integer foodId = dish.getDishId();
                String description = dish.getDescription();
                String name = dish.getDishName();
                Double price = dish.getPrice();
                ArrayList<JSONObject> specfoods = new ArrayList<>();
                JSONObject specfood = new JSONObject();
                specfood.put("name",name);
                specfood.put("food_id",foodId);
                specfood.put("price",price);
                specfoods.add(specfood);
                JSONObject dishs = new JSONObject();
                dishs.put("image_url", imageUrl);
                dishs.put("food_id", foodId);
                dishs.put("descriptio", description);
                dishs.put("name", name);
                dishs.put("specfoods",specfoods);
                foods.add(dishs);
            });

            ca.put("name", type);
            ca.put("foods", foods);
            data.add(ca);
        });

        return R.success(data);
    }

    @GetMapping("/food/orderlist")
    public R tableOrderById(@RequestParam("table_id") Integer tableId){
        Map<String, ArrayList<JSONObject>> data = new HashMap<>();
        ArrayList<JSONObject> list = new ArrayList<>();
        List<Orders> ordersList = orderService.selectByStatus(tableId, 0);
        ordersList.forEach(item ->{
            JSONObject listDetail = new JSONObject();
            Integer orderId = item.getOrderId();
            Orders orders = orderService.getById(orderId);
            listDetail.put("order_id", orderId);
            Map<String, ArrayList<JSONObject>> foods = new HashMap<>();
            ArrayList<JSONObject> foodList = new ArrayList<>();
            List<Trans> translist=JSONArray.parseArray(item.getContent(),Trans.class);

            translist.forEach(content->{
                Integer foodId = content.getFoods_id();
                Integer number = content.getNumber();
                Dish dish = new Dish();
                dish = dishService.getById(foodId);
                String name = dish.getDishName();
                Double price = dish.getPrice();
                Double total = item.getTotalPrice();
                LocalDateTime time = orders.getCreateTime();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("money", total);
                jsonObject.put("date", time.toString().split("T")[0]);
                jsonObject.put("time", time.toString().split("T")[1]);
                jsonObject.put("describe","等"+translist.size()+"件商品");
                foodList.add(jsonObject);
            });

            listDetail.put("foods", foodList);
            list.add(listDetail);
        });
        data.put("list",list);
        return R.success(data);
    }

    @GetMapping("/order/add")
    public R add(@RequestParam(value = "remarkmessage", required = false)String remarkmessage, @RequestParam("translist") String content,
                 @RequestParam("price_total") Double priceTotal, @RequestParam("table_id") Integer tableId){

        Orders orders = new Orders();
        //orders.setOrderId(UUID.randomUUID().toString());
        orders.setTableId(tableId);
        orders.setRemarkmessage(remarkmessage);
        orders.setTotalPrice(priceTotal);
        orders.setContent(content);
        orderService.save(orders);
        return R.success();
    }

    @GetMapping("/order/detail")
    public R detail(@RequestParam("order_id") Integer orderId){
        Map<String, ArrayList<JSONObject>> data = new HashMap<>();
        ArrayList<JSONObject> translist = new ArrayList<>();
        Orders orders = orderService.getById(orderId);
        List<Trans> translists=JSONArray.parseArray(orders.getContent(),Trans.class);
        translists.forEach(content->{
            Integer foodId = content.getFoods_id();
            Integer number = content.getNumber();
            Dish dish = new Dish();
            dish = dishService.getById(foodId);
            String name = dish.getDishName();
            String imageUrl = dish.getImageUrl();
            Double price = dish.getPrice();
            Double total = price * number;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("imge_url", imageUrl);
            jsonObject.put("shopvalue",price);
            jsonObject.put("price", total);
            jsonObject.put("foods_id", foodId);
            jsonObject.put("number",number);
            translist.add(jsonObject);
        });
        data.put("translist", translist);
        return R.success(data);
    }


}
