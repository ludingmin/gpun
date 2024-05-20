package com.gpnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpnu.entity.User;
import com.gpnu.mapper.UserMapper;
import com.gpnu.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
