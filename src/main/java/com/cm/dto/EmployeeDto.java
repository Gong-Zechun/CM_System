package com.cm.dto;

import com.cm.pojo.Employee;

import java.util.Date;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 15:29
 */
public class EmployeeDto extends Employee {
    private String entryDateStr;

    private String leaveDateStr;

    public String getEntryDateStr() {
        return entryDateStr;
    }

    public void setEntryDateStr(String entryDateStr) {
        this.entryDateStr = entryDateStr;
    }

    public String getLeaveDateStr() {
        return leaveDateStr;
    }

    public void setLeaveDateStr(String leaveDateStr) {
        this.leaveDateStr = leaveDateStr;
    }
}
