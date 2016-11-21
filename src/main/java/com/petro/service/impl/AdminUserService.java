package com.petro.service.impl;

import com.petro.dao.AdminUserMapper;
import com.petro.pojo.AdminUser;
import com.petro.service.IAdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 15:37
 */
@Service
public class AdminUserService implements IAdminUserService {

    @Resource
    private AdminUserMapper adminUserDao;
    @Override
    public int selectAdminUser(Map paramMap){
        int resultFlag = 1;
        AdminUser adminUser = this.adminUserDao.selectAdminUser(paramMap);
        if(adminUser == null) {
            resultFlag = 0;
        }
        return resultFlag;
    }
}
