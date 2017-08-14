package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

import java.util.List;

public class ViewFileAction extends EmpBaseAction {
    // 封装所有文件的List
    private List files;
    //想要查找文件的名字
    private String name;

    // files的setter和getter方法
    public void setFiles(List files) {
        this.files = files;
    }

    public List getFiles() {
        return this.files;
    }

    // name的setter和getter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String search() {
        if (name == null) return SUCCESS;
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        setFiles(mgr.getFilesByName(mgrName, name));
        return SUCCESS;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        setFiles(mgr.getFiles(mgrName));
        return SUCCESS;
    }
}
