package com.jsf_login.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MstfDryl
 */
@ManagedBean
@RequestScoped
public class SessionBean {
    
    private Long userId;
    private String userName;
    private String email;
    
    private final HttpServletRequest httpServletRequest; 
    private final FacesContext facesContext;
    private FacesMessage facesMessage;
    
    
    public SessionBean(){
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try
        {
            if((Boolean) httpServletRequest.getSession().getAttribute("sessionSession") == true)
            {

                userId = (Long) httpServletRequest.getSession().getAttribute("sessionUserId");
                userName = httpServletRequest.getSession().getAttribute("sessionUserName").toString();
                email = httpServletRequest.getSession().getAttribute("sessionEmail").toString();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public String logout(){
        httpServletRequest.getSession().invalidate();
        return "index.xhtml?faces-redirect=true";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
