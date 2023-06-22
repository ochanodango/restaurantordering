package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Admin;
import com.ochanodango.restaurantordering.mapper.AdminMapper;
import com.ochanodango.restaurantordering.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
