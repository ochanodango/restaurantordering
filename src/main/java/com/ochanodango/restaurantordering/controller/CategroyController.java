package com.ochanodango.restaurantordering.controller;


import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Categroy;
import com.ochanodango.restaurantordering.service.CategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategroyController {
    @Autowired
    private CategroyService categroyService;

    @GetMapping("/list")
    public R list(){
        List<Categroy> list =  categroyService.list();
        return R.success(list, "菜品分类列表");
    }

    @GetMapping("/add")
    public R addType(@RequestParam("type") String type){
        if(categroyService.existType(type)){
            return R.fail("类型重复");
        }else {
            Categroy categroy = new Categroy();
            categroy.setType(type);
            categroyService.save(categroy);
            return R.success("添加分类成功");
        }
    }

    @PutMapping("/update")
    public R update(@RequestParam("oldType") String oldType, @RequestParam("newType") String newType){
        if(categroyService.existType(oldType)){
            Categroy categroy = categroyService.selectByType(oldType);
            categroy.setType(newType);
            categroyService.saveOrUpdate(categroy);
            return R.success("修改分类成功");
        }
        return R.fail("修改分类失败");
    }

    @DeleteMapping("/delete")
    public R delete(@RequestParam("type") String type){
        if(categroyService.removeByType(type)){
            return R.success("删除分类成功");
        }
        return R.fail("删除分类失败");
    }
}
