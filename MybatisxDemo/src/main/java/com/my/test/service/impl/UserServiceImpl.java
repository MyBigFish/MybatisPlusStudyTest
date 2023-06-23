package com.my.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.test.domain.User;
import com.my.test.service.UserService;
import com.my.test.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author shulongliu
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-06-23 11:18:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




