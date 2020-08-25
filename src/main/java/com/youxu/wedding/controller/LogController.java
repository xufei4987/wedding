package com.youxu.wedding.controller;

import com.youxu.wedding.response.RespMsg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LogController {

    @ExceptionHandler(value = Exception.class)
    public RespMsg errorHandler(Exception ex) {
        RespMsg respMsg = new RespMsg(false,"500", ex.getMessage());
        return respMsg;
    }

}
