package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;
import fisher.vo.AttendBean;

import java.util.List;

public class ViewUnAttendAction extends EmpBaseAction {
    private List<AttendBean> unAttend;

    // unAttend的setter和getter方法
    public void setUnAttend(List<AttendBean> unAttend) {
        this.unAttend = unAttend;
    }

    public List<AttendBean> getUnAttend() {
        return this.unAttend;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String user = (String) ctx.getSession()
                .get(WebConstant.USER);
        List<AttendBean> result = mgr.unAttend(user);
        setUnAttend(result);
        return SUCCESS;
    }
}
