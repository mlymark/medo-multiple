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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "resc")
public class Resc implements Serializable{
    
     public Resc() {
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name")
    private String name; 
    
    @Column(name="res_type")
    private String res_type; 
    
    @Column(name="res_string")
    private String res_string; 
    
    @Column(name="descn")
    private String descn;
    
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "resc_role",
    joinColumns = @JoinColumn(name = "resc_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonBackReference
    private Set<Role> roles;

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

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public String getRes_string() {
        return res_string;
    }

    public void setRes_string(String res_string) {
        this.res_string = res_string;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
