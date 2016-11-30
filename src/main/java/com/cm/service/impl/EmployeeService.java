package com.cm.service.impl;

import com.cm.dao.EmployeeMapper;
import com.cm.pojo.Employee;
import com.cm.service.IEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 14:23
 */
@Service
public class EmployeeService implements IEmployeeService {
    @Resource
    private EmployeeMapper employeeDao;

    //查询所有用户
    @Override
    public List<Employee> selectAllEmployee() {
        return employeeDao.selectAllEmployee();
    }
}
