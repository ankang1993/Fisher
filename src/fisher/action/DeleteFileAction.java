package fisher.action;

import fisher.action.base.EmpBaseAction;

public class DeleteFileAction extends EmpBaseAction {
    // 代表删除文件ID的成员变量
    private int fileId;

    // file的setter和getter方法
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return this.fileId;
    }

    public String execute()
            throws Exception {
        // 删除文件
        mgr.deleteFile(fileId);
        addActionMessage("DELETE SUCCESSFULLY.");
        return SUCCESS;
    }
}