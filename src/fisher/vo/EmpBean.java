package fisher.vo;

import java.io.Serializable;

public class EmpBean implements Serializable {
    private static final long serialVersionUID = 48L;

    // 成员变量
    private int empId;
    private String empName;
    private String empRealName;
    private String empPass;
    private String phone;


    // 无参数的构造器
    public EmpBean() {
    }

    // 初始化全部成员变量的构造器
    public EmpBean(int empId, String empName, String empRealName, String empPass, String phone) {
        this.empId = empId;
        this.empName = empName;
        this.empRealName = empRealName;
        this.empPass = empPass;
        this.phone = phone;
    }

    // empId的setter和getter方法
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return this.empId;
    }

    // empName的setter和getter方法
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return this.empName;
    }

    // empRealName的setter和getter方法
    public String getEmpRealName() {
        return empRealName;
    }

    public void setEmpRealName(String empRealName) {
        this.empRealName = empRealName;
    }

    // empPass的setter和getter方法
    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public String getEmpPass() {
        return this.empPass;
    }

    // phone的setter和getter方法
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }
}