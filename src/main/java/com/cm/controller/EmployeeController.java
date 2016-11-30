package com.cm.controller;

import com.cm.dto.EmployeeDto;
import com.cm.pojo.Employee;
import com.cm.service.IEmployeeService;
import com.cm.service.impl.EmployeeService;
import com.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 员工信息管理页面Controller
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 14:11
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Resource
    private IEmployeeService employeeService;

    @RequestMapping("main")
    public String index(Model model) {
        try{
            List<Employee>  employeeList = employeeService.selectAllEmployee();
            List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
            //转换entryDate和leaveDate格式
            for(Employee meta : employeeList) {
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(meta, employeeDto);
                employeeDto.setEntryDateStr(DateUtil.dateToStr(meta.getEntryDate()));
                employeeDto.setLeaveDateStr(DateUtil.dateToStr(meta.getLeaveDate()));
                employeeDtoList.add(employeeDto);
            }
            model.addAttribute("employeeDtoList", employeeDtoList);
            return "employeeManage";
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
