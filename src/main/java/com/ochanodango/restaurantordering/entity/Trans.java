package com.ochanodango.restaurantordering.entity;

import lombok.Data;

@Data
public class Trans {
    private String name;
    private Double price;
    private Double shopvalue;
    private Integer foods_id;
    private String image_url;
    private Integer number;

}
