package fisher.action;

import fisher.action.base.EmpBaseAction;

import java.util.List;

public class AppChangeAction extends EmpBaseAction {
    // 申请异动的出勤ID
    private int attid;
    // 封装所有异动的列表
    private List types;

    // attId的setter和getter方法
    public void setAttid(int attid) {
        this.attid = attid;
    }

    public int getAttid() {
        return this.attid;
    }

    // types的setter和getter方法
    public void setTypes(List types) {
        this.types = types;
    }

    public List getTypes() {
        return this.types;
    }

    // 处理用户请求
    public String execute()
            throws Exception {
        setTypes(mgr.getAllType());
        return SUCCESS;
    }
}