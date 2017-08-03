package fisher.dao;

import fisher.domain.File;

import java.io.Serializable;

public interface FileDao extends BaseDao<File> {
    /**
     * 根据名字查询文件
     *
     * @param id 文件的id
     * @return 符合名字的文件
     */
    File findByName(Serializable id);
}
