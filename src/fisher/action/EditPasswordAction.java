package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

public class EditPasswordAction extends EmpBaseAction {
    // 原密码
    private String originalPass;
    // 新密码
    private String newPass;
    // 确认密码
    private String confirmPass;

    public EditPasswordAction() {

    }

    public EditPasswordAction(String originalPass, String newPass, String confirmPass) {
        this.originalPass = originalPass;
        this.newPass = newPass;
        this.confirmPass = confirmPass;
    }
    // originalPass的setter和getter方法
    public String getOriginalPass() {
        return originalPass;
    }

    public void setOriginalPass(String originalPass) {
        this.originalPass = originalPass;
    }
    // newPass的setter和getter方法
    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
    // confirmPass的setter和getter方法
    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // 判断确认密码是否和新密码相等
        if (!confirmPass.equals(newPass)) {
            addActionMessage(getText("password.inconsis"));
            return "failure";
        }
        // 修改密码
        String res = mgr.editPassword(mgrName, newPass, originalPass);
        // 如果原密码错误则返回
        if (res.equals("passwrong")) {
            addActionMessage(getText("password.wrong"));
            return "failure";
        }
        // 修改成功
        addActionMessage(getText("edit.success"));
        return SUCCESS;
    }
}
