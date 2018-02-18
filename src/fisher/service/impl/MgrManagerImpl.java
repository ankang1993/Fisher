package fisher.service.impl;

import fisher.dao.*;
import fisher.domain.*;
import fisher.exception.HrException;
import fisher.service.MgrManager;
import fisher.vo.AppBean;
import fisher.vo.AttendBean;
import fisher.vo.EmpBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MgrManagerImpl
        implements MgrManager {
    // dao组件的成员变量
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;

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

    /**
     * 新增员工
     *
     * @param emp 新增的员工
     * @param mgr 员工所属的经理
     */
    public String addEmp(Employee emp, String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        List<Employee> emps = empDao.findAll(Employee.class);
        // 如果用户名已存在则返回
        if (emps.contains(emp)) return "duplicate";
        emp.setManager(m);
        empDao.save(emp);
        return "success";
    }

    /**
     * 删除员工
     *
     * @param empId 删除员工的ID
     * @param mgr 员工所属的经理
     */
    public void deleteEmp(int empId, String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        empDao.delete(Employee.class, empId);
    }

    /**
     * 根据经理返回该部门的全部员工
     *
     * @param mgr 经理名
     * @return 经理的全部下属
     */
    public List<EmpBean> getEmpsByMgr(String mgr)
            throws HrException {
        Manager m = mgrDao.findByName(mgr);
        // 查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        // 封装VO集
        List<EmpBean> result = new ArrayList<EmpBean>();
        // 部门没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        for (Employee e : emps) {
            result.add(new EmpBean(e.getId(), e.getName(),
                    e.getRealname(), e.getPass(), e.getPhone()));
        }
        return result;
    }

    /**
     * 根据员工名返回经理部门的同名员工
     *
     * @param mgr 经理名
     * @param name 员工名
     * @return 对应的员工
     */
    public List<EmpBean> getEmpsByName(String mgr,String name) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        // 查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        // 封装VO集
        List<EmpBean> result = new ArrayList<EmpBean>();
        // 部门没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        for (Employee e : emps) {
            if (e.getRealname().equals(name)) {
                result.add(new EmpBean(e.getId(), e.getName(),
                        e.getRealname(), e.getPass(), e.getPhone()));
            }
        }
        return result;
    }

    /**
     * 根据经理返回该部门的没有批复的申请
     *
     * @param mgr 经理名
     * @return 该部门的全部申请
     */
    public List<AppBean> getAppsByMgr(String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        // 查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        // 封装VO集
        List<AppBean> result = new ArrayList<AppBean>();
        // 部门没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        for (Employee e : emps) {
            // 查看该员工的全部申请
            List<Application> apps = appDao.findByEmp(e);
            if (apps != null && apps.size() > 0) {
                for (Application app : apps) {
                    // 只选择还未处理的申请
                    if (app.getResult() == false) {
                        Attend attend = app.getAttend();
                        result.add(new AppBean(app.getId(),
                                e.getName(), e.getRealname(), attend.getType().getName(),
                                app.getType().getName(), app.getReason()));
                    }
                }
            }
        }
        return result;
    }

    /**
     * 处理申请
     *
     * @param appid   申请ID
     * @param mgrName 经理名字
     * @param result  是否通过
     */
    public void check(int appid, String mgrName, boolean result) {
        Application app = appDao.get(Application.class, appid);
        CheckBack check = new CheckBack();
        check.setApp(app);
        Manager manager = mgrDao.findByName(mgrName);
        check.setManager(manager);
        // 同意通过申请
        if (result) {
            // 设置通过申请
            check.setResult(true);
            // 修改申请为已经批复
            app.setResult(true);
            appDao.update(app);
            // 为真时，还需要修改出勤的类型
            Attend attend = app.getAttend();
            attend.setType(app.getType());
            attendDao.update(attend);
        } else {
            // 没有通过申请
            check.setResult(false);
            app.setResult(true);
            appDao.update(app);
        }
        // 保存申请批复
        checkDao.save(check);
    }

    /**
     * 根据经理返回该部门的员工的未打卡情况
     *
     * @param mgr 经理名
     * @return 经理的下属前七天的未打卡情况
     */
    public List<AttendBean> getPunchsByMgr(String mgr) {
        Manager m = mgrDao.findByName(mgr);
        // 查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        // 封装VO集
        List<AttendBean> result = new ArrayList<AttendBean>();
        // 部门没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        // 找出正常上班
        AttendType type = typeDao.get(AttendType.class, 1);
        for (Employee e : emps) {
            // 找出非正常上班的出勤记录
            List<Attend> attends = attendDao.findByEmpUnAttend(e, type);
            List<AttendBean> list = new ArrayList<AttendBean>();
            // 封装VO集合
            for (Attend att : attends) {
                list.add(new AttendBean(att.getId(), e.getName(), e.getRealname(), att.getDutyDay()
                        , att.getType().getName(), att.getPunchTime()));
            }
            if (list.size() > 0) result.addAll(list);
        }
        return result;
    }
}


