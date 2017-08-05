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
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;
    private FileDao fileDao;

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
        } else {
            return LOGIN_FAIL;
        }
    }

    /**
     * 自动打卡，周一到周五，早上7：00、中午12：00为每个员工插入旷工记录
     */
    public void autoPunch() {
        System.out.println("自动插入旷工记录");
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
            } else {
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
        } else if (attends.size() == 1
                && attends.get(0).getPunchTime() == null) {
            return LEAVE_PUNCH;
        } else if (attends.size() == 2) {
            // 可以上班、下班打卡
            if (attends.get(0).getPunchTime() == null
                    && attends.get(1).getPunchTime() == null) {
                return BOTH_PUNCH;
            }
            // 可以下班打卡
            else if (attends.get(1).getPunchTime() == null) {
                return LEAVE_PUNCH;
            } else {
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
        System.out.println("============打卡==========");
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
            // 18点之后算正常
            if (punchHour >= LEAVE_LIMIT) {
                attend.setType(typeDao.get(AttendType.class, 1));
            }
            // 16~18点之间算早退
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
            result.add(new AttendBean(att.getId(), empName, att.getDutyDay()
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
        System.out.println("--------------" + attId);
        System.out.println("~~~~" + typeId);
        System.out.println("~~~~" + reason);
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
        System.out.println("====添加申请====");
        appDao.save(app);
        return true;
    }

    /**
     * 返回全部文件
     *
     * @param mgr 员工名
     * @return 全部文件
     */
    public List<FileBean> getFiles(String mgr)
            throws HrException {
        Employee m = empDao.findByName(mgr);
        if (m == null) {
            throw new HrException("Do you have logged in?");
        }
        List<File> files = fileDao.findAll(File.class);
        //封装VO集
        List<FileBean> result = new ArrayList<FileBean>();
        for (File f : files) result.add(new FileBean(f.getName(), f.getId()));
        return result;
    }

    /**
     * 下载指定文件
     *
     * @param fileId 文件名
     * @return File文件
     */
    public File downloadFile(int fileId)
            throws HrException {
        File file = fileDao.get(File.class, fileId);
        return file;
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
        File file = new File();
        file.setName(fileName);
        file.setAddress(path);
        file.setType(type);
        fileDao.save(file);
    }

    /**
     * 删除指定文件
     *
     * @param fileId 文件名
     * @return
     */
    public void deleteFile(int fileId)
            throws HrException {
        File file = fileDao.get(File.class, fileId);
        // 返回指定文件对应的真实地址
        String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/files") + "\\" + file.getAddress();
        new java.io.File(realPath).delete();
        fileDao.delete(file);
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
        if (!emp.getPass().equals(originalPass)) return "passwrong";
        emp.setPass(newPass);
        return "success";
    }
}