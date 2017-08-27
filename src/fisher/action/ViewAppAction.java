package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.MgrBaseAction;

import java.util.List;

public class ViewAppAction extends MgrBaseAction {
    // 封装所有申请的List
    private List apps;

    // apps的setter和getter方法
    public void setApps(List apps) {
        this.apps = apps;
    }

    public List getApps() {
        return this.apps;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        // 获取需要被当前经理处理的全部申请
        setApps(mgr.getAppsByMgr(mgrName));
        return SUCCESS;
    }
}