package com.petro.service;

import com.petro.pojo.AdminUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 15:37
 */
public interface IAdminUserService {
    int selectAdminUser(Map paramMap);
}
