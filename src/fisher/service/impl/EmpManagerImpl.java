package fisher.service.impl;

import fisher.dao.*;
import fisher.domain.*;
import fisher.exception.HrException;
import fisher.service.EmpManager;
import fisher.vo.AttendBean;
import fisher.vo.FileBean;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmpManagerImpl
        implements EmpManager {
    // dao组件的成员变量
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;
    private FileDao fileDao;

    // dao组件的setter方法
    public void setAppDao(ApplicationDao appDao) {
        this.appDao = appDao;
    }

    public void setAttendDao(AttendDao attendDao) {
        this.attendDao = attendDao;
    }

    public void setTypeDao(AttendTypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public void setCheckDao(CheckBackDao checkDao) {
        this.checkDao = checkDao;
    }

    public void setEmpDao(EmployeeDao empDao) {
        this.empDao = empDao;
    }

    public void setMgrDao(ManagerDao mgrDao) {
        this.mgrDao = mgrDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    /**
     * 以经理身份来验证登录
     *
     * @param mgr 登录的经理身份
     * @return 登录后的身份确认:0为登录失败，1为登录emp 2为登录mgr
     */
    public int validLogin(Manager mgr) {
        // 如果找到一个经理，以经理登录
        if (mgrDao.findByNameAndPass(mgr).size() >= 1) {
            return LOGIN_MGR;
        }
        // 如果找到普通员工，以普通员工登录
        else if (empDao.findByNameAndPass(mgr).size() >= 1) {
            return LOGIN_EMP;
        }
        else {
            return LOGIN_FAIL;
        }
    }

    /**
     * 自动打卡，周一到周五，早上7：00、中午12：00为每个员工插入旷工记录
     */
    public void autoPunch() {
        List<Employee> emps = empDao.findAll(Employee.class);
        // 获取当前时间
        String dutyDay = new java.sql.Date(
                System.currentTimeMillis()).toString();
        for (Employee e : emps) {
            // 获取旷工对应的出勤类型
            AttendType atype = typeDao.get(AttendType.class, 6);
            Attend a = new Attend();
            a.setDutyDay(dutyDay);
            a.setType(atype);
            // 如果当前时间是是早上，对应于上班打卡
            if (Calendar.getInstance()
                    .get(Calendar.HOUR_OF_DAY) < AM_LIMIT) {
                // 上班打卡
                a.setIsCome(true);
            }
            else {
                // 下班打卡
                a.setIsCome(false);
            }
            a.setEmployee(e);
            attendDao.save(a);
        }
    }

    /**
     * 验证某个员工是否可打卡
     *
     * @param user    员工名
     * @param dutyDay 日期
     * @return 可打卡的类别
     */
    public int validPunch(String user, String dutyDay) {
        // 不能查找到对应用户，返回无法打卡
        Employee emp = empDao.findByName(user);
        if (emp == null) {
            return NO_PUNCH;
        }
        // 找到员工当前的出勤记录
        List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
        // 系统没有为用户在当天插入空打卡记录，无法打卡
        if (attends == null || attends.size() <= 0) {
            return NO_PUNCH;
        }
        // 开始上班打卡
        else if (attends.size() == 1
                && attends.get(0).getIsCome()
                && attends.get(0).getPunchTime() == null) {
            return COME_PUNCH;
        }
        // 开始下班打卡
        else if (attends.size() == 1
                && attends.get(0).getPunchTime() == null) {
            return LEAVE_PUNCH;
        }
        // 可以上班、下班打卡
        else if (attends.size() == 2) {
            // 可以上班、下班打卡
            if (attends.get(0).getPunchTime() == null
                    && attends.get(1).getPunchTime() == null) {
                return BOTH_PUNCH;
            }
            // 可以下班打卡
            else if (attends.get(1).getPunchTime() == null) {
                return LEAVE_PUNCH;
            }
            else {
                return NO_PUNCH;
            }
        }
        return NO_PUNCH;
    }

    /**
     * 打卡
     *
     * @param user    员工名
     * @param dutyDay 打卡日期
     * @param isCome  是否是上班打卡
     * @return 打卡结果
     */
    public int punch(String user, String dutyDay, boolean isCome) {
        Employee emp = empDao.findByName(user);
        if (emp == null) {
            return PUNCH_FAIL;
        }
        // 找到员工本次打卡对应的出勤记录
        Attend attend =
                attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
        if (attend == null) {
            return PUNCH_FAIL;
        }
        // 已经打卡
        if (attend.getPunchTime() != null) {
            return PUNCHED;
        }
        // 获取打卡时间
        int punchHour = Calendar.getInstance()
                .get(Calendar.HOUR_OF_DAY);
        attend.setPunchTime(new Date());
        // 上班打卡
        if (isCome) {
            // 9点之前算正常
            if (punchHour < COME_LIMIT) {
                attend.setType(typeDao.get(AttendType.class, 1));
            }
            // 9～11点之间算迟到
            else if (punchHour < LATE_LIMIT) {
                attend.setType(typeDao.get(AttendType.class, 4));
            }
            // 11点之后算旷工,无需理会
        }
        // 下班打卡
        else {
            // 17点之后算正常
            if (punchHour >= LEAVE_LIMIT) {
                attend.setType(typeDao.get(AttendType.class, 1));
            }
            // 16~17点之间算早退
            else if (punchHour >= EARLY_LIMIT) {
                attend.setType(typeDao.get(AttendType.class, 5));
            }
        }
        attendDao.update(attend);
        return PUNCH_SUCC;
    }

    /**
     * 员工查看自己的最近七天非正常打卡
     *
     * @param empName 员工名
     * @return 该员工的最近七天的非正常打卡
     */
    public List<AttendBean> unAttend(String empName) {
        // 找出正常上班
        AttendType type = typeDao.get(AttendType.class, 1);
        Employee emp = empDao.findByName(empName);
        // 找出非正常上班的出勤记录
        List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
        List<AttendBean> result = new ArrayList<AttendBean>();
        // 封装VO集合
        for (Attend att : attends) {
            result.add(new AttendBean(att.getId(), empName, emp.getRealname(), att.getDutyDay()
                    , att.getType().getName(), att.getPunchTime()));
        }
        return result;
    }

    /**
     * 返回全部的出勤类别
     *
     * @return 全部的出勤类别
     */
    public List<AttendType> getAllType() {
        return typeDao.findAll(AttendType.class);
    }

    /**
     * 添加申请
     *
     * @param attId  申请的出勤ID
     * @param typeId 申请的类型ID
     * @param reason 申请的理由
     * @return 添加的结果
     */
    public boolean addApplication(int attId, int typeId
            , String reason) {
        // 创建一个申请
        Application app = new Application();
        // 获取申请需要改变的出勤记录
        Attend attend = attendDao.get(Attend.class, attId);
        AttendType type = typeDao.get(AttendType.class, typeId);
        app.setAttend(attend);
        app.setType(type);
        if (reason != null) {
            app.setReason(reason);
        }
        appDao.save(app);
        return true;
    }

    /**
     * 返回文件总数
     *
     * @return 文件总数
     */
    public long getCount() throws HrException {
        return fileDao.findCount(MyFile.class);
    }

    /**
     * 返回全部文件
     *
     * @param page 页码
     * @param pageSize 每页数量
     * @return 全部文件
     */
    public List<FileBean> getFiles(int page, int pageSize)
            throws HrException {
        List<MyFile> myFiles = fileDao.findByPage("select e from MyFile e", page, pageSize);
        //封装VO集
        List<FileBean> result = new ArrayList<FileBean>();
        for (MyFile f : myFiles) {
            result.add(new FileBean(f.getName(), f.getId()));
        }
        return result;
    }

    /**
     * 返回符合名字要求的文件
     *
     * @param name 文件名
     * @return 符合名字要求的全部文件
     */
    public List<FileBean> getFilesByName(String name) throws HrException {
        List<MyFile> myFiles = fileDao.findAll(MyFile.class);
        //封装VO集
        List<FileBean> result = new ArrayList<FileBean>();
        for (MyFile f : myFiles) {
            if (f.getName().contains(name)) {
                result.add(new FileBean(f.getName(), f.getId()));
            }
        }
        return result;
    }

    /**
     * 下载指定文件
     *
     * @param fileId 文件名
     * @return File文件
     */
    public MyFile downloadFile(int fileId)
            throws HrException {
        MyFile myFile = fileDao.get(MyFile.class, fileId);
        return myFile;
    }

    /**
     * 上传新文件
     *
     * @param fileName 文件名
     * @param path 文件地址
     * @param type 文件类型
     * @return
     */
    public void uploadFile(String fileName, String path, String type) throws HrException {
        //新建File记录并保存
        MyFile myFile = new MyFile();
        myFile.setName(fileName);
        myFile.setAddress(path);
        myFile.setType(type);
        fileDao.save(myFile);
    }

    /**
     * 删除指定文件
     *
     * @param fileId 文件名
     * @return
     */
    public void deleteFile(int fileId)
            throws HrException {
        MyFile myFile = fileDao.get(MyFile.class, fileId);
        // 返回指定文件对应的真实地址
        String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/files") + "\\" + myFile.getAddress();
        // 删除文件
        new java.io.File(realPath).delete();
        // 删除数据库记录
        fileDao.delete(MyFile.class, fileId);
    }

    /**
     * 修改密码
     *
     * @param mgrName 员工名
     * @param newPass 新密码
     * @return
     */
    public String editPassword(String mgrName, String newPass, String originalPass) {
        Employee emp = empDao.findByName(mgrName);
        // 如果原密码错误则返回
        if (!emp.getPass().equals(originalPass)) return "passwrong";
        emp.setPass(newPass);
        return "success";
    }
}