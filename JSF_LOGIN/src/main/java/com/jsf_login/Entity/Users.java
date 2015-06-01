/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf_login.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MstfDryl
 */
@Entity(name="USERS")

@NamedQueries({
    @NamedQuery(name="xUserNameAndyPassword" , query="SELECT u FROM USERS u WHERE u.status=1 and u.userName=:userName and u.password=:password"),
   /*
    @NamedQuery(name="xUserName" , query="SELECT u FROM USERS u WHERE u.status=1 and u.userName=:userName"),
    @NamedQuery(name="xEmail" , query="SELECT u FROM USERS u WHERE u.status=1 and u.email=:email"),
*/
    @NamedQuery(name="allUsers", query="SELECT u FROM USERS u")

})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(unique=true, name="USERNAME",nullable=false)
    private String userName;
    
    @Column(name="PASSWORD",nullable=false)
    private String password;
    
    @Column(unique=true, name="EMAIL",nullable=false)
    private String email;
    
    @Column(name="STATUS",nullable=false)
    private Boolean status = true;

    public Users() {
    }
    
    public Users(String userName, String password, String email, Boolean status) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
