package com.cm.service;

import com.cm.pojo.Employee;

import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 14:22
 */
public interface IEmployeeService {
    //查询所有用户
    List<Employee> selectAllEmployee();
}
