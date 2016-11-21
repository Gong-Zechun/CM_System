package com.cm.dao;

import com.cm.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 关联--Employee.xml
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 14:19
 */
@Repository
public interface EmployeeMapper {

    //查询所有用户
    List<Employee> selectAllEmployee();
}
