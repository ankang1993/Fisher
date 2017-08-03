package fisher.action;

import fisher.action.base.EmpBaseAction;

public class DeleteFileAction extends EmpBaseAction {
    // ����ɾ���ļ�ID�ĳ�Ա����
    private int fileId;

    // file��setter��getter����
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return this.fileId;
    }

    public String execute()
            throws Exception {
        // ɾ���ļ�
        mgr.deleteFile(fileId);
        addActionMessage("DELETE SUCCESSFULLY.");
        return SUCCESS;
    }
}