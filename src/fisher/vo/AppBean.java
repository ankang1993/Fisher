package fisher.vo;

import java.io.Serializable;

public class AppBean implements Serializable {
    private static final long serialVersionUID = 48L;

    // 成员变量
    private int id;
    private String empName;
    private String empRealName;
    private String unAttend;
    private String toAttend;
    private String reason;


    // 无参数的构造器
    public AppBean() {
    }

    // 初始化全部成员变量的构造器
    public AppBean(int id, String empName, String empRealName, String unAttend
            , String toAttend, String reason) {
        this.id = id;
        this.empName = empName;
        this.empRealName = empRealName;
        this.unAttend = unAttend;
        this.toAttend = toAttend;
        this.reason = reason;
    }

    // id的setter和getter方法
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    // empName的setter和getter方法
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return this.empName;
    }

    // empRealName的setter和getter方法
    public void setEmpRealName(String empRealName) {
        this.empRealName = empRealName;
    }

    public String getEmpRealName() {
        return this.empRealName;
    }

    // unAttend的setter和getter方法
    public void setUnAttend(String unAttend) {
        this.unAttend = unAttend;
    }

    public String getUnAttend() {
        return this.unAttend;
    }

    // toAttend的setter和getter方法
    public void setToAttend(String toAttend) {
        this.toAttend = toAttend;
    }

    public String getToAttend() {
        return this.toAttend;
    }

    // reason的setter和getter方法
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

}
