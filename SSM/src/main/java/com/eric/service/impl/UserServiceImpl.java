package com.eric.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.eric.bean.User;
import com.eric.dao.UserMapper;
import com.eric.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userDao;


    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }
}
