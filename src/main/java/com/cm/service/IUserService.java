package com.cm.service;

import com.cm.pojo.User;

import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-9-13 10:35
 */
public interface IUserService {
    //通过id查询用户
    User selectById(int userId);
    //通过id删除用户
    int deleteById(int userId);
    //插入用户信息
    int insertUser(User user);
    //更新用户信息
    int updateById(User user);

    List<User> selectAllUser();
}
