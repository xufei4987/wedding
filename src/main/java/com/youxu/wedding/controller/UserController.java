package com.youxu.wedding.controller;


import com.youxu.wedding.entity.User;
import com.youxu.wedding.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Youux
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") Integer id){
        return userService.getById(id);
    }
}
