package fisher.action;

import fisher.action.base.EmpBaseAction;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

public class UploadFileAction extends EmpBaseAction {

    // 封装上传文件域的属性
    private File myFile;
    // 封装上传文件类型的属性
    private String myFileContentType;
    // 封装上传文件名的属性
    private String myFileFileName;

    // myFile的setter和getter方法
    public File getMyFile() {
        return myFile;
    }
    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    // myFileContentType的setter和getter方法
    public String getMyFileContentType() {
        return myFileContentType;
    }
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    // myFileFileName的setter和getter方法
    public String getMyFileFileName() {
        return myFileFileName;
    }
    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    @Override
    public String execute() throws Exception {
        if (myFile == null) return SUCCESS;
        // 以服务器的文件保存地址和原文件名建立上传文件输出流
        // 得到保存上传文件的目录的真实路径
        File dir = new File(ServletActionContext.getServletContext().getRealPath("/WEB-INF/files"));
        // 如果该目录不存在，就创建
        if (!dir.exists()) dir.mkdirs();
        String name = UUID.randomUUID().toString();
        String address = dir + "\\" + name;
        FileOutputStream fos = new FileOutputStream(address);
        FileInputStream fis = new FileInputStream(getMyFile());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0)
        {
            fos.write(buffer , 0 , len);
        }
        fos.close();
        fis.close();
        mgr.uploadFile(getMyFileFileName(), name, getMyFileContentType());
        return SUCCESS;
    }
}