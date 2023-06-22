package com.ochanodango.restaurantordering.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject){
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject){
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
