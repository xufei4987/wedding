package com.youxu.wedding.service;

import com.youxu.wedding.response.RespMsg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PhotosService {

    RespMsg upload(MultipartFile file);

    List<RespMsg> uploadBatch(HttpServletRequest request);
}
