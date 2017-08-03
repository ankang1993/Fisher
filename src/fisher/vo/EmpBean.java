package fisher.vo;

import java.io.Serializable;

public class EmpBean implements Serializable {
    private static final long serialVersionUID = 48L;
    private int empId;
    private String empName;
    private String empPass;
    private String phone;


    // 无参数的构造器
    public EmpBean() {
    }

    // 初始化全部成员变量的构造器
    public EmpBean(int empId, String empName, String empPass, String phone) {
        this.empId = empId;
        this.empName = empName;
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