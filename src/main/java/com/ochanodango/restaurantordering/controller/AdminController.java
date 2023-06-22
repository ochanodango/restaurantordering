package com.ochanodango.restaurantordering.controller;

import com.fasterxml.jackson.databind.util.Converter;
import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.Admin;
import com.ochanodango.restaurantordering.mapper.AdminMapper;
import com.ochanodango.restaurantordering.service.AdminService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/signin")
    public R sigin(@RequestParam("id") Integer id, @RequestParam("password") String password,
                   @RequestParam("email") String email){
        if(adminService.getById(id) != null ){
            Admin admin = new Admin();
            admin.setId(id);
            admin.setPassword(password);
            admin.setEmail(email);
            adminService.save(admin);
            return R.success("注册成功");
        }else {
            return R.fail("注册失败");
        }
    }

    @PostMapping("/login")
    public R login(@RequestParam("id") Integer id, @RequestParam("password") String password ){
        if(adminService.getById(id) != null ){
           Admin admin = adminService.getById(id);
           if(admin.getPassword().equals(password)){
               return R.success("登陆成功");
           }
        }
        return R.fail("登录失败");
    }

}
