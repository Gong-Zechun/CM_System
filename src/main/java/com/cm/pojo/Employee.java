package com.cm.pojo;

import com.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-10 14:01
 */
public class Employee {
    /**
     * 主键id
     */
    private int id;

    /**
     * 工号
     */
    private String empNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 一级部门
     */
    private String depart1;

    /**
     * 二级部门
     */
    private String depart2;

    /**
     * 三级部门
     */
    private String depart3;

    /**
     * 四级部门
     */
    private String depart4;

    /**
     * 办公所在地
     */
    private String workLocation;

    /**
     * 员工的职级，例如员工级，主管级，经理级
     */
    private String postPole;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 离职日期
     */
    private Date leaveDate;

    /**
     * 员工状态（试用；转正;实习;离职）
     */
    private String empState;

    /**
     * 剩余加班总时长
     */
    private BigDecimal overTimesCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart1() {
        return depart1;
    }

    public void setDepart1(String depart1) {
        this.depart1 = depart1;
    }

    public String getDepart2() {
        return depart2;
    }

    public void setDepart2(String depart2) {
        this.depart2 = depart2;
    }

    public String getDepart3() {
        return depart3;
    }

    public void setDepart3(String depart3) {
        this.depart3 = depart3;
    }

    public String getDepart4() {
        return depart4;
    }

    public void setDepart4(String depart4) {
        this.depart4 = depart4;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getPostPole() {
        return postPole;
    }

    public void setPostPole(String postPole) {
        this.postPole = postPole;
    }

//    public Date getEntryDate() {
//        return entryDate;
//    }
//
//    public void setEntryDate(Date entryDate) {
//        String entryDateStr = DateUtil.dateToStr(entryDate);
//        Date date = DateUtil.strToDate(entryDateStr);
//        this.entryDate = date;
//    }


    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getEmpState() {
        return empState;
    }

    public void setEmpState(String empState) {
        this.empState = empState;
    }

    public BigDecimal getOverTimesCount() {
        return overTimesCount;
    }

    public void setOverTimesCount(BigDecimal overTimesCount) {
        this.overTimesCount = overTimesCount;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", empNo='" + empNo + '\'' +
                ", name='" + name + '\'' +
                ", depart1='" + depart1 + '\'' +
                ", depart2='" + depart2 + '\'' +
                ", depart3='" + depart3 + '\'' +
                ", depart4='" + depart4 + '\'' +
                ", workLocation='" + workLocation + '\'' +
                ", postPole='" + postPole + '\'' +
                ", entryDate=" + entryDate +
                ", leaveDate=" + leaveDate +
                ", empState='" + empState + '\'' +
                ", overTimesCount=" + overTimesCount +
                '}';
    }
}
