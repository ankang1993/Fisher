package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

public class ProcessPunchAction extends EmpBaseAction {

    // 处理上班打卡的方法
    public String come()
            throws Exception {
        return process(true);
    }

    // 处理下班打卡的方法
    public String leave()
            throws Exception {
        return process(false);
    }

    private String process(boolean isCome)
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String user = (String) ctx.getSession().get(WebConstant.USER);
        String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
        // 调用业务逻辑方法处理打卡请求
        int result = mgr.punch(user, dutyDay, isCome);
        switch (result) {
            case 0:
                addActionMessage(getText("punch.fail"));
                break;
            case 1:
                addActionMessage(getText("punch.before"));
                break;
            case 2:
                addActionMessage(getText("punch.success"));
                break;
        }
        return SUCCESS;
    }
}
