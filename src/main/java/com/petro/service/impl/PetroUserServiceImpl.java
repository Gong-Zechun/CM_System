package com.petro.service.impl;

import com.petro.dao.PetroUserMapper;
import com.petro.pojo.PetroUser;
import com.petro.service.IPetroUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-31 13:40
 */
@Service
public class PetroUserServiceImpl implements IPetroUserService{

    @Resource
    private PetroUserMapper petroUserDao;

    @Override
    public int insertPetroUser(PetroUser petroUser){
        return this.petroUserDao.insertPetroUser(petroUser);
    }

    @Override
    public List<PetroUser> selectAllPetroUser() {
        return this.petroUserDao.selectAllPetroUser();
    }

//    @Override
//    public PetroUser selectPetroUserById(int id) {
//        return null;
//    }

    @Override
    public int updatePetroUserById(PetroUser petroUser) {
        return this.petroUserDao.updatePetroUserById(petroUser);
    }

    @Override
    public int deleteById(int userId){
        return this.petroUserDao.deleteById(userId);
    }
}
