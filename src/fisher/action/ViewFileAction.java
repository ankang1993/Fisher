package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

import java.util.List;

public class ViewFileAction extends EmpBaseAction {
    // 封装所有文件的List
    private List files;
    //想要查找文件的名字
    private String name;
    //page
    private int page;
    //count
    private long pageCount;
    //pageSize
    private int pageSize = 20;
    //是否是查询操作
    private int flag;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String search() {
        if (name == null) return SUCCESS;
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        setFiles(mgr.getFilesByName(mgrName, name));
        setFlag(1);
        return SUCCESS;
    }

    public String execute()
            throws Exception {
        // 创建ActionContext实例
        ActionContext ctx = ActionContext.getContext();
        // 获取HttpSession中的user属性
        String mgrName = (String) ctx.getSession()
                .get(WebConstant.USER);
        if (pageCount == 0) {
            long tmp = mgr.getCount(mgrName);
            if (tmp % pageSize == 0) tmp /= pageSize;
            else tmp = tmp / pageSize + 1;
            setPageCount(tmp);
        }
        if (page < 1 || page > pageCount) page = 1;
        setFiles(mgr.getFiles(mgrName, page, pageSize));
        setFlag(0);
        return SUCCESS;
    }
}
