package fisher.service.impl;

import fisher.dao.*;
import fisher.domain.*;
import fisher.exception.HrException;
import fisher.service.MgrManager;
import fisher.vo.AppBean;
import fisher.vo.EmpBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MgrManagerImpl
        implements MgrManager {
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;

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
    public void addEmp(Employee emp, String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        if (m == null) {
            throw new HrException("Are you Boss? Or do you have logged in?");
        }
        emp.setManager(m);
        empDao.save(emp);
    }

    /**
     * 删除员工
     *
     * @param empId 删除员工的ID
     * @param mgr 员工所属的经理
     */
    public void deleteEmp(int empId, String mgr) throws HrException {
        Manager m = mgrDao.findByName(mgr);
        if (m == null) {
            throw new HrException("Are you Boss? Or do you have logged in?");
        }
        Employee emp = empDao.get(Employee.class, empId);
        empDao.delete(emp);
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
        if (m == null) {
            throw new HrException("Are you Boss? Or do you have logged in?");
        }
        //查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        //封装VO集
        List<EmpBean> result = new ArrayList<EmpBean>();
        //部门依然没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        for (Employee e : emps) {
            result.add(new EmpBean(e.getId(), e.getName(),
                    e.getPass(), e.getPhone()));
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
        if (m == null) {
            throw new HrException("Are you Boss? Or do you have logged in?");
        }
        //查询该经理对应的全部员工
        Set<Employee> emps = m.getEmployees();
        //封装VO集
        List<AppBean> result = new ArrayList<AppBean>();
        //部门依然没有员工
        if (emps == null || emps.size() < 1) {
            return result;
        }
        for (Employee e : emps) {
            //查看该员工的全部申请
            List<Application> apps = appDao.findByEmp(e);
            if (apps != null && apps.size() > 0) {
                for (Application app : apps) {
                    //只选择还未处理的申请
                    if (app.getResult() == false) {
                        Attend attend = app.getAttend();
                        result.add(new AppBean(app.getId(),
                                e.getName(), attend.getType().getName(),
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
        if (manager == null) {
            throw new HrException("Are you Boss? Or do you have logged in?");
        }
        check.setManager(manager);
        //同意通过申请
        if (result) {
            //设置通过申请
            check.setResult(true);

            //修改申请为已经批复
            app.setResult(true);
            appDao.update(app);
            //为真时，还需要修改出勤的类型
            Attend attend = app.getAttend();
            attend.setType(app.getType());
            attendDao.update(attend);
        } else {
            //没有通过申请
            check.setResult(false);
            app.setResult(true);
            appDao.update(app);
        }
        //保存申请批复
        checkDao.save(check);
    }
}


