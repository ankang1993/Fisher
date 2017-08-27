package fisher.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AK on 2017/08/01.
 */

@Entity
@Table(name = "employee_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name = "emp_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Employee implements Serializable {
    private static final long serialVersionUID = 48L;
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 标识属性
    private Integer id;
    // 员工用户名
    @Column(name = "emp_name", nullable = false, length = 50, unique = true)
    private String name;
    // 员工密码
    @Column(name = "emp_pass", nullable = false, length = 50)
    private String pass;
    // 员工姓名
    @Column(name = "emp_realname", nullable = false, length = 50)
    private String realname;
    // 员工电话
    @Column(name = "emp_phone", nullable = false, length = 50)
    private String phone;
    // 员工对应的经理
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name = "mgr_id")
    private Manager manager;
    // 员工对应的出勤记录
    @OneToMany(targetEntity = Attend.class, mappedBy = "employee")
    private Set<Attend> attends = new HashSet<>();

    // 无参数的构造器
    public Employee() {
    }

    // 初始化全部成员变量的构造器
    public Employee(Integer id, String name, String pass,
                    String realname, String phone, Manager manager,
                    Set<Attend> attends) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.realname = realname;
        this.phone = phone;
        this.manager = manager;
        this.attends = attends;
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

    // pass的setter和getter方法
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }

    // realname的setter和getter方法
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    // salary的setter和getter方法
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    // manager的setter和getter方法
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return this.manager;
    }

    // attends的setter和getter方法
    public void setAttends(Set<Attend> attends) {
        this.attends = attends;
    }

    public Set<Attend> getAttends() {
        return this.attends;
    }

    // 根据name来重写hashCode()方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    // 根据name来重写equals()方法，只要name相同的员工即认为相等。
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }
}