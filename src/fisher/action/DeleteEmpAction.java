package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.MgrBaseAction;

public class DeleteEmpAction extends MgrBaseAction {
    // ����ɾ��Ա��ID�ĳ�Ա����
    private int empId;

    // emp��setter��getter����
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return this.empId;
    }

    public String execute()
            throws Exception {
        // ����ActionContextʵ��
        ActionContext ctx = ActionContext.getContext();
        // ��ȡHttpSession�е�user����
        String mgrName = (String) ctx.getSession().get(WebConstant.USER);
        // ɾ��Ա��
        mgr.deleteEmp(empId, mgrName);
        addActionMessage("DELETE SUCCESSFULLY.");
        return SUCCESS;
    }
}