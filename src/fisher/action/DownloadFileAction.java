package fisher.action;

import fisher.action.base.EmpBaseAction;
import fisher.domain.MyFile;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DownloadFileAction extends EmpBaseAction {
    // 代表下载文件ID的成员变量
    private int fileId;
    // 代表下载文件的成员变量
    private MyFile myFile;

    // fileId的setter和getter方法
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return this.fileId;
    }

    // MyFile的setter和getter方法
    public void setMyFile(MyFile myFile) {
        this.myFile = myFile;
    }

    public MyFile getMyFile() {
        return myFile;
    }

    /*
    定义一个返回InputStream的方法，
    该方法将作为被下载文件的入口，
    且需要配置stream类型结果时指定inputName参数，
    inputName参数的值就是方法去掉get前缀、首字母小写的字符串
    */
    public InputStream getTargetFile() throws Exception {
        this.myFile = mgr.downloadFile(getFileId());
        // ServletContext提供getResourceAsStream()方法
        // 返回指定文件对应的输入流
        String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/files") + "\\" + myFile.getAddress();
        return new FileInputStream(new File(realPath));
    }
    // 返回文件名字
    public String getFileName() throws Exception {
        String downFileName = myFile.getName();
        downFileName = new String(downFileName.getBytes(), "ISO8859-1");
        return downFileName;
    }
}