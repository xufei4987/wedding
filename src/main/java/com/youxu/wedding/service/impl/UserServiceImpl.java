package com.youxu.wedding.service.impl;

import com.youxu.wedding.entity.User;
import com.youxu.wedding.mapper.UserMapper;
import com.youxu.wedding.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Youux
 * @since 2020-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
