package com.youxu.wedding.controller;

import com.youxu.wedding.response.RespMsg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test")
    public RespMsg test(){
        return new RespMsg(true,"200","请求成功");
    }
}
