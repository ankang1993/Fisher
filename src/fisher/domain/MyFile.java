package fisher.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "file_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MyFile implements Serializable {
    private static final long serialVersionUID = 48L;
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 标识属性
    private Integer id;
    // 文件名字
    @Column(name = "file_name", nullable = false)
    private String name;
    // 文件地址
    @Column(name = "file_address", nullable = false)
    private String address;
    // 文件类型
    @Column(name = "file_type", nullable = false)
    private String type;

    // 无参数的构造器
    public MyFile() {
    }

    // 初始化全部成员变量的构造器
    public MyFile(Integer id, String name, String address, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    // id的setter和getter方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    // name的setter和getter方法
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // type的setter和getter方法
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    // type的setter和getter方法
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // 根据name、address来重写hashCode()方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

    // 根据name、address来重写equals()方法，只要name、address相同的文件即认为相等。
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MyFile other = (MyFile) obj;
        if (name == null) {
            if (other.getName() != null) return false;
        } else if (!name.equals(other.getName())) return false;
        if (address == null) {
            if (other.getAddress() != null) return false;
        } else if (!address.equals(other.getAddress())) return false;
        return true;
    }
}