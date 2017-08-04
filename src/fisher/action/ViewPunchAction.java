package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.MgrBaseAction;

import java.util.List;

public class ViewPunchAction extends MgrBaseAction {
    private List punchs;

    // punchs的setter和getter方法
    public List getPunchs() {
        return punchs;
    }

    public void setPunchs(List punchs) {
        this.punchs = punchs;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        // 获取需要被当前经理处理的全部申请
        setPunchs(mgr.getPunchsByMgr(mgrName));
        return SUCCESS;
    }
}