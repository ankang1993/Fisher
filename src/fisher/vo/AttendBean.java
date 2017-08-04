package fisher.vo;

import java.io.Serializable;
import java.util.Date;

public class AttendBean implements Serializable {
    private static final long serialVersionUID = 48L;
    private int id;
    private String empName;
    private String dutyDay;
    private String unType;
    private Date time;

    // 无参数的构造器
    public AttendBean() {
    }

    // 初始化全部成员变量的构造器
    public AttendBean(int id, String empName, String dutyDay
            , String unType, Date time) {
        this.id = id;
        this.empName = empName;
        this.dutyDay = dutyDay;
        this.unType = unType;
        this.time = time;
    }

    // id的setter和getter方法
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    //empName的setter和getter方法
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    // dutyDay的setter和getter方法
    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public String getDutyDay() {
        return this.dutyDay;
    }

    // unType的setter和getter方法
    public void setUnType(String unType) {
        this.unType = unType;
    }

    public String getUnType() {
        return this.unType;
    }

    // time的setter和getter方法
    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return this.time;
    }
}
