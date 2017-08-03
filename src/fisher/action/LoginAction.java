package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;
import fisher.domain.Manager;

public class LoginAction extends EmpBaseAction {
    // 定义一个常量作为员工登录成功的Result名
    private final String EMP_RESULT = "emp";
    // 定义一个常量作为经理登录成功的Result名
    private final String MGR_RESULT = "mgr";
    // 封装请求参数
    private Manager manager;
    // 登录的验证码
    private String vercode;

    // manager的setter和getter方法
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return this.manager;
    }

    // vercode的setter和getter方法
    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public String getVercode() {
        return this.vercode;
    }

    // 处理用户请求
    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的rand属性
        String ver2 = (String) ctx.getSession().get("rand");
        if (vercode.equalsIgnoreCase(ver2)) {
            // 调用业务逻辑方法来处理登录请求
            int result = mgr.validLogin(getManager());
            // 登录结果为普通员工
            if (result == 1) {
                ctx.getSession().put(WebConstant.USER
                        , manager.getName());
                ctx.getSession().put(WebConstant.LEVEL
                        , WebConstant.EMP_LEVEL);
                addActionMessage("You have successfully logged in.");
                return EMP_RESULT;
            }
            // 登录结果为经理
            else if (result == 2) {
                ctx.getSession().put(WebConstant.USER
                        , manager.getName());
                ctx.getSession().put(WebConstant.LEVEL
                        , WebConstant.MGR_LEVEL);
                return MGR_RESULT;
            }
            // 用户名和密码不匹配
            else {
                addActionMessage("Wrong Account or Password");
                return ERROR;
            }
        }
        // 验证码不匹配
        addActionMessage("Wrong Identifying Code");
        return ERROR;
    }
}
