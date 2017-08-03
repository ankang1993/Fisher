package fisher.action.base;

import com.opensymphony.xwork2.ActionSupport;
import fisher.service.MgrManager;

public class MgrBaseAction extends ActionSupport {
    protected MgrManager mgr;

    public void setMgrManager(MgrManager mgr) {
        this.mgr = mgr;
    }
}
