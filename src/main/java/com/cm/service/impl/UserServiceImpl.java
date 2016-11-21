package com.cm.service.impl;

import com.cm.dao.UserMapper;
import com.cm.pojo.User;
import com.cm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-9 10:42
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userDao;

    @Override
    public User selectById(int userId) {
        return this.userDao.selectById(userId);
    }

    @Override
    public int deleteById(int userId) {
        return this.userDao.deleteById(userId);
    }

    @Override
    public int insertUser(User user) {
        return this.userDao.insertUser(user);
    }

    @Override
    public int updateById(User user) {
        return this.userDao.updateById(user);
    }

    @Override
    public List<User> selectAllUser(){
        return this.userDao.selectAllUser();
    }
}
