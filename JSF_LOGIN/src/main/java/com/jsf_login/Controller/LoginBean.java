/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf_login.Controller;

import com.jsf_login.EJB.LoginEJB;
import com.jsf_login.Entity.Users;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MstfDryl
 */
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
    
    Users user;
    
    @EJB
    private LoginEJB loginEJB;
    
    private final HttpServletRequest httpServletRequest; 
    private final FacesContext facesContext;
    private FacesMessage facesMessage;
    
    public LoginBean(){
        user = new Users();
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }
    
    
    public String loginControl(){
        try
        {
            user = loginEJB.loginControl(user.getUserName(),user.getPassword());
            if(user==null)
            {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Kullanıcı Adı veya Parola Hatalı"); 
                facesContext.addMessage(null, facesMessage);
                user = new Users();
                return null;
            }
            else
            {
                httpServletRequest.getSession().setAttribute("sessionSession", true);
                httpServletRequest.getSession().setAttribute("sessionUserId", user.getId());
                httpServletRequest.getSession().setAttribute("sessionUserName", user.getUserName());
                httpServletRequest.getSession().setAttribute("sessionEmail", user.getEmail());
                return "default.xhtml?faces-redirect=true";
            }
        }
        catch(Exception e)
        {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Kullanıcı Adı veya Parola Hatalı"); 
            facesContext.addMessage(null, facesMessage);
            return null;
        }
        
    }
    
    public String logoutPage(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    
}
