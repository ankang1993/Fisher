package fisher.dao;

import fisher.domain.MyFile;

import java.io.Serializable;

public interface FileDao extends BaseDao<MyFile> {
    /**
     * 根据名字查询文件
     *
     * @param id 文件的id
     * @return 符合名字的文件
     */
    MyFile findById(Serializable id);

}
