package com.cm.dao;

import com.cm.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-9 10:37
 */
@Repository
public interface UserMapper {
    //通过id查询用户
    User selectById(int id);
    //通过id删除用户
    int deleteById(int id);
    //插入用户信息
    int insertUser(User user);
    //更新用户信息
    int updateById(User user);

    //查询所有用户
    List<User> selectAllUser();
}
