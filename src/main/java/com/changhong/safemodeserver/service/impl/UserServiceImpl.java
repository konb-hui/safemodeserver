package com.changhong.safemodeserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changhong.safemodeserver.entity.User;
import com.changhong.safemodeserver.mapper.UserMapper;
import com.changhong.safemodeserver.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author konb
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
