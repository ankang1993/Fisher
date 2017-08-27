package fisher.action;

import fisher.action.base.MgrBaseAction;
import fisher.domain.Employee;

public class AddEmpAction extends MgrBaseAction {
    // 代表新增员工的成员变量
    private Employee emp;
    // 代表确认密码
    private String confirmpass;

    // emp的setter和getter方法
    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getEmp() {
        return this.emp;
    }

    // confimpass的setter和getter方法
    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String execute()
            throws Exception {
        // 判断确认密码与密码是否相等
        if (!confirmpass.equals(emp.getPass())) {
            addActionMessage(getText("password.inconsis"));
            return "failure";
        }
        // 添加新用户
        String res = mgr.addEmp(emp, "admin");
        // 如果用户名已存在返回
        if (res.equals("duplicate")) {
            addActionMessage(getText("user.exist"));
            return "failure";
        }
        // 注册成功
        addActionMessage(getText("register.success"));
        return SUCCESS;
    }
}