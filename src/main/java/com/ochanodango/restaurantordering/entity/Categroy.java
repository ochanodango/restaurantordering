package com.ochanodango.restaurantordering.entity;

import com.baomidou.mybatisplus.annotation.TableId;

public class Categroy {
    @TableId(value = "categroy_id")
    private int categroyId;
    private String type;

    public Categroy(int categroyId, String type) {
        this.categroyId = categroyId;
        this.type = type;
    }

    public Categroy() {
    }

    public int getCategroyId() {
        return categroyId;
    }

    public void setCategroyId(int categroyId) {
        this.categroyId = categroyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
