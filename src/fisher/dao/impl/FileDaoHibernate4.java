package fisher.dao.impl;

import fisher.dao.FileDao;
import fisher.domain.MyFile;

import java.io.Serializable;
import java.util.List;

public class FileDaoHibernate4 extends BaseDaoHibernate4<MyFile>
        implements FileDao {
    /**
     * 根据名字查询文件
     *
     * @param id 文件的名字
     * @return 符合名字的文件
     */
    public MyFile findById(Serializable id) {
        List<MyFile> myFiles = find("select e from MyFile e where e.id = ?0"
                , id);
        if (myFiles != null && myFiles.size() >= 1) {
            return myFiles.get(0);
        }
        return null;
    }
}
