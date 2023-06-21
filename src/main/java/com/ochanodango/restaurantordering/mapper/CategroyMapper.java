package com.ochanodango.restaurantordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochanodango.restaurantordering.entity.Categroy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface CategroyMapper extends BaseMapper<Categroy>{

}
