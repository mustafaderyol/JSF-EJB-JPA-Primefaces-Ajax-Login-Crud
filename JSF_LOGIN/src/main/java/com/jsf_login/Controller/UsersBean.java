package com.jsf_login.Controller;

import com.jsf_login.EJB.UsersEJB;
import com.jsf_login.Entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author MstfDryl
 */

@ManagedBean(name="usersBean")
@RequestScoped
public class UsersBean {
    
    FacesContext context = FacesContext.getCurrentInstance();
        
    private Users user;
    
    @EJB
    UsersEJB userEJB;
    
    private List<Users> userList = new ArrayList();
    
    private final FacesContext facesContext;
    private FacesMessage facesMessage;
    
    @PostConstruct
    public void getAllUsersList(){
        user = new Users();
        userList = userEJB.allUsers();
    }
    
    public void onRowCancel(){
        
        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"BİLGİ ! ","Güncelleme İşlemi İptal Edildi."); 
        facesContext.addMessage(null, facesMessage);
    }
    
    public UsersBean(){
        facesContext = FacesContext.getCurrentInstance();
    }
    
    public String editUser(Users userEdit){
        if(!userEdit.getEmail().contains("@") || !userEdit.getEmail().contains("."))
        {
            
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Geçersiz Email Adresi"); 
            facesContext.addMessage(null, facesMessage);
            return null;
        }

        try
        {
            userEJB.editUsers(userEdit);
            userList = userEJB.allUsers();
            
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"BİLGİ ! ","Kayıt Başarı ile Güncellendi... \n "); 
            facesContext.addMessage(null, facesMessage);
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {   
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Kullanıcı Güncellerken Hata Oluştu... \n "+e); 
            facesContext.addMessage(null, facesMessage);
            return null;
        }
        
    }
    
    public String addUser(){
        try
        {
            if(!user.getEmail().contains("@") || !user.getEmail().contains("."))
            {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,"DİKKAT ! ","Geçerli e-Mail değil"); 
                facesContext.addMessage(null, facesMessage);
                return null;
            }
                
            
            user = userEJB.saveUser(user);
            user = new Users();
            userList = userEJB.allUsers();
            
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"BİLGİ ! ","Kullanıcı Başarı ile Eklendi..."); 
            facesContext.addMessage(null, facesMessage);
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Kullanıcı Kayıtlı... \n "+e); 
            facesContext.addMessage(null, facesMessage);
            return null;
        }
    }

    public String deleteUser(Users getUser){
        try
        {
            userEJB.deleteUsers(getUser);
            userList = userEJB.allUsers();
            
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"BİLGİ ! ","Kullanıcı Başarı ile Silindi..."); 
            facesContext.addMessage(null, facesMessage);
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"HATA ! ","Kullanıcı Silinirken Hata Oluştu... \n "+e); 
            facesContext.addMessage(null, facesMessage);
            return null;
        }
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    
    
}
