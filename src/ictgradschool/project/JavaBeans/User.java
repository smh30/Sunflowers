package ictgradschool.project.JavaBeans;

import javax.servlet.ServletContext;
import java.io.Serializable;

public class User implements Serializable {
    String username;

    // no argument constructor needed for JavaBean
    public User(){
    
    }

    //
    public User (String username){
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
