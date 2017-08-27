package fisher.action;

import com.opensymphony.xwork2.ActionContext;
import fisher.action.base.EmpBaseAction;

import java.util.List;

public class ViewFileAction extends EmpBaseAction {
    // 封装所有文件的List
    private List files;
    // 想要查找文件的名字
    private String name;
    // 当前是第几页
    private int page;
    // 页面数量
    private long pageCount;
    // 总的记录数
    private long count;
    // 每页显示记录数
    private int pageSize = 20;
    // 是否是查询操作
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

    // page的setter和getter方法
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    // count的setter和getter方法
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    // pageCount的setter和getter方法
    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    // flag的setter和getter方法
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    // 查找文件
    public String search() {
        if (name == null) return SUCCESS;
        // 查找文件
        setFiles(mgr.getFilesByName(name));
        // 设置标志为查询操作
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
        // 设置pageCount
        if (pageCount == 0) {
            count = mgr.getCount();
            long tmp = 0;
            if (count % pageSize == 0) tmp /= pageSize;
            else tmp = tmp / pageSize + 1;
            setPageCount(tmp);
        }
        // 如果不存在记录，则置pageCount为1
        if (pageCount == 0) pageCount = 1;
        if (page < 1 || page > pageCount) page = 1;
        setFiles(mgr.getFiles(page, pageSize));
        // 设置标志为查看所有文件操作
        setFlag(0);
        return SUCCESS;
    }
}
