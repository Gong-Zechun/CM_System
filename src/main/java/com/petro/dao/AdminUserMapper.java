package com.petro.dao;

import com.petro.pojo.AdminUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 15:32
 */
@Repository
public interface AdminUserMapper {
    AdminUser selectAdminUser(Map paramMap);
}
