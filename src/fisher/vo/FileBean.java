package fisher.vo;

import java.io.Serializable;

public class FileBean implements Serializable {
    private static final long serialVersionUID = 48L;

    // 成员变量
    private String fileName;
    private int fileId;


    // 无参数的构造器
    public FileBean() {
    }

    // 初始化全部成员变量的构造器
    public FileBean(String fileName, int fileId) {
        this.fileName = fileName;
        this.fileId = fileId;
    }

    // fileName的setter和getter方法
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    // fileAdress的setter和getter方法
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileId() {
        return this.fileId;
    }
}