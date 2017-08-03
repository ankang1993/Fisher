package fisher.vo;

import java.io.Serializable;

public class PhoneBean
        implements Serializable {
    private static final long serialVersionUID = 48L;
    private String empName;
    private String phone;

    // 无参数的构造器
    public PhoneBean() {
    }

    // 初始化全部成员变量的构造器
    public PhoneBean(String empName, String phone) {
        this.empName = empName;
        this.phone = phone;
    }

    // empName的setter和getter方法
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return this.empName;
    }

    // amount的setter和getter方法
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

}
