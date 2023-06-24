package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Categroy;
import com.ochanodango.restaurantordering.entity.Dish;
import com.ochanodango.restaurantordering.service.CategroyService;
import com.ochanodango.restaurantordering.service.DishService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        System.out.println(list.toString());
        return R.success(list, "所有菜品列表");
    }

    @GetMapping("/listCategory")
    public R listCategory(){
        List<Categroy> categroys = categroyService.list();
        ArrayList<JSONObject> list = new ArrayList<>();
        categroys.forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            Integer categroyId = item.getCategroyId();
            String type = item.getType();
            List<Dish> children = dishService.selectByCategoryId(categroyId);
            jsonObject.put("categoryId", categroyId);
            jsonObject.put("type", type);
            jsonObject.put("children", children);
            list.add(jsonObject);
        });
        return R.success(list);
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

    @PutMapping("/update")
    public R update(@RequestParam("dishId") Integer dishId, @RequestParam(value = "dishName", required = false) String dishName,
                    @RequestParam(value = "description", required = false) String description, @RequestParam(value = "price",required = false) Double price,
                    @RequestParam(value = "imageUrl",required = false) String imageUrl, @RequestParam(value = "categoryId",required = false) Integer categoryId,
                    @RequestParam(value = "status",required = false)Integer status){
        Dish dish = dishService.getById(dishId);
        if(dish != null){
            if(dishName != null){
                dish.setDishName(dishName);
            }
            if(description != null){
                dish.setDescription(description);
            }
            if(price != null){
                dish.setPrice(price);
            }
            if(imageUrl != null){
                dish.setImageUrl(imageUrl);
            }
            if (categoryId != null) {
                dish.setCategoryId(categoryId);
            }
            if(status != null){
                dish.setStatus(status);
            }
            if(dishService.saveOrUpdate(dish)){
                return R.success("菜品修改成功");
            }
        }
        return R.fail("菜品修改失败");
    }

    @DeleteMapping("/delete")
    public R deteteByName(@RequestParam("dishName") String dishName){
        Integer dishId = dishService.selectDishName(dishName);
        if(dishId != -1){
            if(dishService.removeById(dishId)){
                return R.success("删除菜品成功");
            }
        }
        return R.fail("删除菜品失败");
    }
}
