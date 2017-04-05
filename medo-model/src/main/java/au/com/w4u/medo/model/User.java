package au.com.w4u.medo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mly
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public User(){
        super();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="username",length=32)
    private String username;
    
    @Column(name="type")
    private Integer type;
    
    @Column(name="password")
    private String password;
    
    @Column(name="address")
    private String address;
    
    @Column(name="email")
    private String email;
    
    @Column(name="status")
    private Integer status;
    
    @Column(name="descn")
    private String descn;
    
    @Column(name="create_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;
    
    /**
     * by mly
     * CascadeType 这里不要乱用会导致很多问题，不懂最好看下文档
     * CascadeType.MERGE 这个只是更新，需要保存的role必须是持久化的，不然报错
     * CascadeType.PERSIST 这个和MERGE相反，需要保存的role是游离态的，如果是持久化对象的话报错
     * CascadeType.REMOVE  级联删除role,但是此处删除user的话并不需要删除role
     * FetchType.LAZY  使用懒加载，但是这里的话一个用户关联的Role也不会很多，所以就不懒加载了，
     *                  如果用懒加载的话会出现懒加载的时候session已经关闭了的问题，会导致异常发生，
     *                  这个问题可以用openSessioninView解决，但是openSessioninView又会带来许多问题，不建议使用
     * FetchType.EAGER 不使用懒加载直接查出关联的list
     * @JsonBackReference (Force lazy loading) 由于使用了FetchType.EAGER 如果直接返回将对象转换为json的话，会出现关联数据无限循环，用该标签可以解决这个问题
     * https://github.com/FasterXML/jackson-datatype-hibernate/pull/58
     */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + username + ", password=" + password+", status=" + status+  ", descn=" + descn+", role=" + role.getName()+"]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
