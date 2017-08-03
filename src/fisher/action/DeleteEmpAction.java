package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.MgrBaseAction;

public class DeleteEmpAction extends MgrBaseAction {
    // 代表删除员工ID的成员变量
    private int empId;

    // emp的setter和getter方法
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return this.empId;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // 删除员工
        mgr.deleteEmp(empId, mgrName);
        addActionMessage("DELETE SUCCESSFULLY.");
        return SUCCESS;
    }
}