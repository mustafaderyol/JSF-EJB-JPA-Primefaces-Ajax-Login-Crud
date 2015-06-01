package com.jsf_login.EJB;

import com.jsf_login.Entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author MstfDryl
 */
@Stateless
public class UsersEJB {
    
    @PersistenceContext(name="JSF_LOGIN_PU")
    private EntityManager em;
    
    public List<Users> allUsers(){
        TypedQuery<Users> sorgu = em.createNamedQuery("allUsers", Users.class);
        return sorgu.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users saveUser(Users user){
            em.persist(user);
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users mergeUser(Users user){
        
        em.merge(em.find(Users.class,user.getId()));
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUsers(Users user) throws Exception{
        try
        {
            em.remove(em.merge(user));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users editUsers(Users user) throws Exception {
        try
        {
            em.merge(user);
            em.flush();
            return user;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }
    
}
