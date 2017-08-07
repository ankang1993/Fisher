package fisher.action;

import fisher.action.base.EmpBaseAction;

public class ProcessAppAction extends EmpBaseAction {
    // 申请异动的出勤ID
    private int attId;
    // 希望改变到出勤类型
    private int typeId;
    // 申请理由
    private String reason;

    // attId的setter和getter方法
    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getAttId() {
        return this.attId;
    }

    // typeId的setter和getter方法
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return this.typeId;
    }

    // reason的setter和getter方法
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    // 处理用户请求
    public String execute()
            throws Exception {
        System.out.println(attId);
        // 处理异动申请
        boolean result = mgr.addApplication(attId, typeId, reason);
        // 如果申请成功
        if (result) {
            addActionMessage("You have applied successfully, please wait.");
        } else {
            addActionMessage("You have applied before, please wait.");
        }
        return SUCCESS;
    }
}