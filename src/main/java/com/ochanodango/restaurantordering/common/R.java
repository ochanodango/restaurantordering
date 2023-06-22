package com.ochanodango.restaurantordering.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> R<T> success(){
        return new R<>(200,"success",null);
    }
    public static <T> R<T> success(T data){
        return new R<>(200,"success",data);
    }
    public static <T> R<T> success(T data,String message){
        return new R<>(200,message,data);
    }
    public static <T> R<T> success(String message){
        return new R<>(200,message,null);
    }
    public static<T>  R<T> fail(){
        return new R<>(201,"fail",null);
    }
    public static<T>  R<T> fail(Integer code){
        return new R<>(code,"fail",null);
    }
    public static<T>  R<T> fail(Integer code, String message){
        return new R<>(code,message,null);
    }
    public static<T>  R<T> fail( String message){
        return new R<>(201,message,null);
    }
}
