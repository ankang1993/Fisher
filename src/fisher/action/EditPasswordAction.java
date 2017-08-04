package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

public class EditPasswordAction extends EmpBaseAction {
    private String originalPass;
    private String newPass;
    private String confirmPass;

    public EditPasswordAction() {

    }

    public EditPasswordAction(String originalPass, String newPass, String confirmPass) {
        this.originalPass = originalPass;
        this.newPass = newPass;
        this.confirmPass = confirmPass;
    }

    public String getOriginalPass() {
        return originalPass;
    }

    public void setOriginalPass(String originalPass) {
        this.originalPass = originalPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

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
        // 添加新用户
        if (!confirmPass.equals(newPass)) {
            addActionMessage("Password Inconsistency.");
            return "failure";
        }
        String res = mgr.editPassword(mgrName, newPass, originalPass);
        if (res.equals("passwrong")) {
            addActionMessage("Password Wrong.");
            return "failure";
        }
        addActionMessage("EDIT SUCCESSFULLY.");
        return SUCCESS;
    }
}
