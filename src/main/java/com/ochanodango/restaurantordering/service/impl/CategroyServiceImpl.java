package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Categroy;
import com.ochanodango.restaurantordering.mapper.CategroyMapper;
import com.ochanodango.restaurantordering.service.CategroyService;
import org.springframework.stereotype.Service;

@Service
public class CategroyServiceImpl extends ServiceImpl<CategroyMapper, Categroy> implements CategroyService{

}
