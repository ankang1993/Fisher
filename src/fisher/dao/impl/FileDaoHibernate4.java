package fisher.dao.impl;

import fisher.dao.FileDao;
import fisher.domain.File;

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
}
