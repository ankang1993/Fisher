package fisher.dao.impl;

import fisher.dao.ManagerDao;
import fisher.domain.Manager;

import java.util.List;

public class ManagerDaoHibernate4 extends BaseDaoHibernate4<Manager>
        implements ManagerDao {
    /**
     * 根据用户名和密码查询经理
     *
     * @param emp 包含指定用户名、密码的经理
     * @return 符合指定用户名和密码的经理
     */
    public List<Manager> findByNameAndPass(Manager mgr) {
        return find("select m from Manager m where m.name = ?0 and m.pass=?1"
                , mgr.getName(), mgr.getPass());
    }

    /**
     * 根据用户名查找经理
     *
     * @param name 经理的名字
     * @return 名字对应的经理
     */
    public Manager findByName(String name) {
        List<Manager> ml = find("select m from Manager m where m.name=?0"
                , name);
        if (ml != null && ml.size() > 0) {
            return ml.get(0);
        }
        return null;
    }
}

