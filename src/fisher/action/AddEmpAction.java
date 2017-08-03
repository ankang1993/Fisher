package fisher.action;

import fisher.action.base.MgrBaseAction;
import fisher.domain.Employee;

public class AddEmpAction extends MgrBaseAction {
    // 代表新增员工的成员变量
    private Employee emp;
    //
    private  String confimpass;

    // emp的setter和getter方法
    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getEmp() {
        return this.emp;
    }

    //confimpass的setter和getter方法
    public String getConfimpass() {
        return confimpass;
    }

    public void setConfimpass(String confimpass) {
        this.confimpass = confimpass;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        //ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        //String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // 添加新用户
        if (!confimpass.equals(emp.getPass())) {
            addActionMessage("Password inconsistency.");
            return "failure";
        }
        mgr.addEmp(emp, "admin");
        addActionMessage("REGISTER SUCCESSFULLY.");
        return SUCCESS;
    }
}