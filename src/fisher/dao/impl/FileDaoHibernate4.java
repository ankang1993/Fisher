package fisher.dao.impl;

import fisher.dao.FileDao;
import fisher.domain.File;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public class FileDaoHibernate4 extends BaseDaoHibernate4<File>
        implements FileDao {
    /**
     * 根据名字查询文件
     *
     * @param id 文件的名字
     * @return 符合名字的文件
     */
    public File findByName(Serializable id) {
        List<File> files = find("select e from File e where e.id = ?0"
                , id);
        if (files != null && files.size() >= 1) {
            return files.get(0);
        }
        return null;
    }

    /**
     * 使用hql 语句进行分页查询操作
     *
     * @param params   如果hql带占位符参数，params用于传入占位符参数
     * @param pageNo   查询第pageNo页的记录
     * @param pageSize 每页需要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    public List<File> findByPage(String hql, int pageNo, int pageSize
            , Object... params) {
        // 创建查询
        Query query = getSessionFactory().getCurrentSession()
                .createQuery(hql);
        // 为包含占位符的HQL语句设置参数
        for (int i = 0, len = params.length; i < len; i++) {
            query.setParameter(i + "", params[i]);
        }
        // 执行分页，并返回查询结果
        return query.setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }
}
