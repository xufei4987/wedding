package com.youxu.wedding.controller;

import com.youxu.wedding.response.RespMsg;
import com.youxu.wedding.service.PhotosService;
import com.youxu.wedding.utils.CommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {

    @Resource
    private PhotosService photosService;

    @GetMapping("/test")
    public String test(){
        return CommonUtils.getJarHomePath();
    }

    @PostMapping("/upload")
    public RespMsg upload(MultipartFile file){
        return photosService.upload(file);
    }

    @PostMapping("/upload/batch")
    public List<RespMsg> uploadBatch(HttpServletRequest request){
        return photosService.uploadBatch(request);
    }

}
