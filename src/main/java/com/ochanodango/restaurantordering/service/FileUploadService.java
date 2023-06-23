package com.ochanodango.restaurantordering.service;

import com.ochanodango.restaurantordering.entity.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {
    PicUploadResult uploadImg(MultipartFile uploadFile, HttpServletRequest request);
}
