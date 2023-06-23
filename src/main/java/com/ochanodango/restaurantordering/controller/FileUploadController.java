package com.ochanodango.restaurantordering.controller;

import com.ochanodango.restaurantordering.common.R;
import com.ochanodango.restaurantordering.entity.PicUploadResult;
import com.ochanodango.restaurantordering.service.FileUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/upload")
public class FileUploadController {
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png"};

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/img")
    public R uploadImg(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        boolean isFlag = false;
        for(String type : IMAGE_TYPE){
            System.out.println(file.getOriginalFilename());
            if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)){
                isFlag = true;
                break;
            }
        }

        if (isFlag){
            PicUploadResult picUploadResult = fileUploadService.uploadImg(file, request);
            Integer isError = picUploadResult.getError();

            if(isError == 1){
                Map resMap = new HashMap<>();
                resMap.put("imgPath", picUploadResult.getUrl());
                return R.success(resMap);
            }else {
                return R.fail("图片上传有误");
            }
        }else {
            return R.fail("上传的图片格式必须为:bmp,jpg,jpeg,png");
        }


    }

}
