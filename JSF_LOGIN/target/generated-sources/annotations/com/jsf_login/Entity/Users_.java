package com.jsf_login.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T23:45:40")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Boolean> status;

}