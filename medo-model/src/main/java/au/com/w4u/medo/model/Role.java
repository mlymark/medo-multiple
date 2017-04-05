/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mly
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable{

    public Role() {
    }
    
    public Role(String name , String descn) {
        this.name = name;
        this.descn = descn;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
      
    @Column(name="name")
    private String name;
    
    @Column(name="descn")
    private String descn;
    
    @OneToMany(mappedBy="role",cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;
    
    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Resc> rescs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Resc> getRescs() {
        return rescs;
    }

    public void setRescs(Set<Resc> rescs) {
        this.rescs = rescs;
    }

}
