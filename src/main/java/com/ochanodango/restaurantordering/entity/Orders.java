package com.ochanodango.restaurantordering.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Orders {
    @TableId(value = "order_id",type = IdType.AUTO)
    private Integer orderId;
    @TableField(value = "table_id")
    private Integer tableId;
    private Double totalPrice;
    private Integer status;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private String content;

    private String remarkmessage;

}
