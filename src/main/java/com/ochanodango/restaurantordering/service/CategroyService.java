package com.ochanodango.restaurantordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochanodango.restaurantordering.entity.Categroy;


public interface CategroyService extends IService<Categroy> {

    Boolean existType(String type);
    Categroy selectByType(String type);

    Boolean removeByType(String type);
}
