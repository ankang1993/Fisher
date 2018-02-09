package fisher.service;

import fisher.domain.Employee;
import fisher.exception.HrException;
import fisher.vo.AppBean;
import fisher.vo.AttendBean;
import fisher.vo.EmpBean;

import java.util.List;

public interface MgrManager {
    // 以上午11点为上午时间的截止点
    public static final int AM_LIMIT = 11;

    /**
     * 新增员工
     *
     * @param emp 新增的员工
     * @param mgr 员工所属的经理
     */
    String addEmp(Employee emp, String mgr) throws HrException;

    /**
     * 打卡，为新注册员工插入旷工记录
     */
    void punch(String user);

    /**
     * 删除员工
     *
     * @param empId 删除员工的ID
     * @param mgr 员工所属的经理
     */
    void deleteEmp(int empId, String mgr) throws HrException;

    /**
     * 根据经理返回该部门的全部员工
     *
     * @param mgr 经理名
     * @return 经理的全部下属
     */
    List<EmpBean> getEmpsByMgr(String mgr);

    /**
     * 根据员工名返回经理部门的同名员工
     *
     * @param mgr 经理名
     * @param name 员工名
     * @return 对应的员工
     */
    List<EmpBean> getEmpsByName(String mgr,String name);

    /**
     * 根据经理返回该部门的没有批复的申请
     *
     * @param mgr 经理名
     * @return 该部门的全部申请
     */
    List<AppBean> getAppsByMgr(String mgr);

    /**
     * 处理申请
     *
     * @param appid   申请ID
     * @param mgrName 经理名字
     * @param result  是否通过
     */
    void check(int appid, String mgrName, boolean result);

    /**
     * 根据经理返回该部门的员工的未打卡情况
     *
     * @param mgr 经理名
     * @return 经理的下属前七天的未打卡情况
     */
    List<AttendBean> getPunchsByMgr(String mgr);
}
