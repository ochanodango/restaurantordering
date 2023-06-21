package com.ochanodango.restaurantordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochanodango.restaurantordering.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
