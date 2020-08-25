package com.youxu.wedding.service.impl;

import cn.hutool.core.io.FileUtil;
import com.youxu.wedding.response.RespMsg;
import com.youxu.wedding.service.PhotosService;
import com.youxu.wedding.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotosServiceImpl implements PhotosService {

    @Value("${static.resource.dir}")
    private String resourceDir;

    @Override
    public RespMsg upload(MultipartFile file) {
        if (file == null || file.isEmpty()){
            throw new RuntimeException("upload file is not exist");
        }
        String photosDir = CommonUtils.getJarHomePath() + File.separator + resourceDir;
        File photoFile = new File(photosDir + File.separator + file.getOriginalFilename());
        if(photoFile.exists()){
            throw new RuntimeException("upload file is already exist, file name is " + file.getOriginalFilename());
        }
        FileUtil.mkParentDirs(photoFile);
        try {
            file.transferTo(photoFile);
            return new RespMsg(true,"200","file upload success!");
        } catch (IOException e) {
            throw new RuntimeException("upload file failed");
        }
    }

    @Override
    public List<RespMsg> uploadBatch(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        List<RespMsg> respMsgs = new ArrayList<>();
        for (MultipartFile file : files){
            RespMsg respMsg = null;
            try {
                respMsg = upload(file);
                respMsgs.add(respMsg);
            }catch (Exception e){
                respMsgs.add(new RespMsg(false,"500",e.getMessage()));
            }

        }
        return respMsgs;
    }
}
