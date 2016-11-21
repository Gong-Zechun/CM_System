package com.petro.dao;

import com.petro.pojo.PetroUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-31 13:36
 */
@Repository
public interface PetroUserMapper {
    //添加新用户
    int insertPetroUser(PetroUser petroUser);

    List<PetroUser> selectAllPetroUser();

    //更新用户信息
    int updatePetroUserById(PetroUser petroUser);

    int deleteById(int userId);
}
