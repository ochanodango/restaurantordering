package com.ochanodango.restaurantordering.entity;

import com.baomidou.mybatisplus.annotation.TableId;

public class Dish {
    @TableId(value = "dishId")
    private Integer dishId;
    private String dishName;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer categroy;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCategroy() {
        return categroy;
    }

    public void setCategroy(Integer categroy) {
        this.categroy = categroy;
    }
}
