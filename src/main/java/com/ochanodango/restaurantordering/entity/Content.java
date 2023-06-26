package com.ochanodango.restaurantordering.entity;

import lombok.Data;

@Data
public class Content {
    private Integer contentId;
    private Integer orderId;
    private Integer dishId;
    private Integer num;
}
