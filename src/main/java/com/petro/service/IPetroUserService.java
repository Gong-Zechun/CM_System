package com.petro.service;

import com.petro.pojo.PetroUser;

import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-31 13:39
 */
public interface IPetroUserService {
    //插入用户信息
    int insertPetroUser(PetroUser petroUser);

    //通过id查询用户
//    PetroUser selectPetroUserById(int id);

    //更新用户信息
    int updatePetroUserById(PetroUser petroUser);

    //
    List<PetroUser> selectAllPetroUser();

    //通过id删除用户
    int deleteById(int userId);
}
