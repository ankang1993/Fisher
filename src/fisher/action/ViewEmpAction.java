package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.MgrBaseAction;

import java.util.List;

public class ViewEmpAction extends MgrBaseAction {
    // 封装当前经理所有员工的List
    private List emps;
    //想要查找员工的姓名
    private String name;

    // emps的setter和getter方法
    public void setEmps(List emps) {
        this.emps = emps;
    }

    public List getEmps() {
        return this.emps;
    }
    // name的setter和getter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String search() {
        if (name == null) return SUCCESS;
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        setEmps(mgr.getEmpsByName(mgrName, name));
        return SUCCESS;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        setEmps(mgr.getEmpsByMgr(mgrName));
        return SUCCESS;
    }
}
