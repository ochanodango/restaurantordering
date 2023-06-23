package com.ochanodango.restaurantordering.service.impl;

import com.ochanodango.restaurantordering.entity.PicUploadResult;
import com.ochanodango.restaurantordering.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private String baseFilePath = "";
    @Override
    public PicUploadResult uploadImg(MultipartFile uploadFile, HttpServletRequest request) {
        PicUploadResult picUploadResult = new PicUploadResult();
        if(uploadFile.isEmpty()){
            picUploadResult.setError(0);
            return picUploadResult;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd/");
        String format = sdf.format(new Date());
        File file = new File(baseFilePath + format);
        if(!file.isDirectory()){
            file.mkdirs();
        }
        String name = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."), name.length());

        try {
            File newFile = new File(file.getAbsoluteFile() + File.separator + newName);
            uploadFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + format + newName;
            picUploadResult.setError(1);
            picUploadResult.setUrl(filePath);
            return picUploadResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        picUploadResult.setError(0);
        return picUploadResult;
    }
}
