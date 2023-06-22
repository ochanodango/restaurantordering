package com.ochanodango.restaurantordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochanodango.restaurantordering.entity.Categroy;
import com.ochanodango.restaurantordering.mapper.CategroyMapper;
import com.ochanodango.restaurantordering.service.CategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategroyServiceImpl extends ServiceImpl<CategroyMapper, Categroy> implements CategroyService{
    @Autowired
    private CategroyMapper categroyMapper;
    public Categroy selectByType(String type){
        QueryWrapper<Categroy> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        Categroy categroy = categroyMapper.selectOne(wrapper);
        return categroy;
    }

    public Boolean existType(String type){
        if(selectByType(type) == null){
            return false;
        }
        return true;
    }

    public Boolean removeByType(String type){
        if(existType(type)){
            QueryWrapper<Categroy> wrapper = new QueryWrapper<>();
            wrapper.eq("type", type);
            if(remove(wrapper)){
                return true;
            }
        }
        return false;
    }
}
