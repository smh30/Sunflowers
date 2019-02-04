package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;
import ictgradschool.project.JavaBeans.User;
import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserDAO {
    
    public static boolean checkPassword(String username, String password, ServletContext context) {
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                            System.out.println("username found! " + r.getString(3));
                        /* If a matching row was found, hash the provided password with the retrieved salt and
        repetitions and compare it against the hash from the database.*/
                            // first column is name, already have
                            //todo see if these are working
                            // second column is binary hash, what data type to save it as??
                            byte[] hash = r.getBytes(4);
                            // third column is salt, also binary
                            byte[] salt = r.getBytes(2);
                            int iterations = r.getInt(1);
                            
                            
                            if (Passwords.isExpectedPassword(password.toCharArray(), salt, iterations,
                                    hash)) {
                                /* If the password did match, return true.*/
                                return true;
                            } else {
                                /* If they do not match,  return false.*/
                                return false;
                            }
                        } else {
                            //if no such user, return false;
                            return false;
                        }
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }
    
    public static boolean newUser(String username, String password, ServletContext context) {
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();
                    
                    //this will be true if data was returned, ie the user exists already
                    if (rs.isBeforeFirst()) {
                        System.out.println("user exists already");
                        /*If it does, redirect the client back to the ​register.html document.*/
                        return false;
                        
                    } else {
                        
                        System.out.println("create new user");
                        byte[] salt = Passwords.getNextSalt(32);
                        byte[] hash = Passwords.hash(password.toCharArray(), salt);
                        System.out.println("done hash");
                        try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO user(iteration, salt, username, password)" +
                                "VALUES (?, ?, ?, ?)")) {
                            s2.setInt(1, 100000);
                            s2.setBytes(2, salt);
                            s2.setString(3, username);
                            s2.setBytes(4, hash);
                            
                            
                            s2.execute();
                        }
                        
                        return true;
                        
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }
    
    public static User getUserDetails(String username, ServletContext context) {
        User user = new User();
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                            String USERNAME = r.getString(3);
                            String COUNTRY = r.getString(5);
                            String REALNAME = r.getString(6);
                            String DESC = r.getString(7);
                            String IMAGEURL = r.getString(8);
                            String DEFAULTIMG = r.getString(9);
                            String DOB = r.getString(11);
                            
                            user.setUsername(USERNAME);
                            user.setCountry(COUNTRY);
                            user.setRealName(REALNAME);
                            user.setDescription(DESC);
                            user.setDOB(DOB);
                            user.setPictureURL(IMAGEURL);
                            user.setDefaultImage(DEFAULTIMG);
                            
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
        return null;
    }
    
    
    public static User editUser(String username, String country, String realName,
                                String description, String dateOfBirth, ServletContext context) {
        
        User user = new User();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                
                //todo pretty unsure about using int for the date of birth - surely should be a
                // date??? currently throws exception if no value is given but works in a way

                
                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.user SET country = ? ,real_name=?, description = ?, date_of_birth = ? WHERE ysy.user.username= ?")) {
                    s2.setString(1, country);
                    s2.setString(2, realName);
                    s2.setString(3, description);
                    s2.setString(4, dateOfBirth);
                    s2.setString(5, username);
                    
                    s2.execute();
                    
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    //return false;
                }
                
                
                //  return true;
                return user;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return user;
        }
        return null;
    }
    
    public static void changeDefaultImage(String selectedImage, String username,
                                          ServletContext context) {
        User user = new User();
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                
                
                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.user SET default_image = ? WHERE " +
                        "ysy.user.username= ?")) {
                    s2.setString(1, selectedImage);
                    s2.setString(2, username);
                    s2.execute();
                    
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public static boolean isNameTaken(String toCheck, ServletContext context){
        boolean taken = true;
    
        Properties dbProps = DAOCheckProperties.check(context);
    
        if (dbProps != null) {
        
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
            
            
                try (PreparedStatement s2 = conn.prepareStatement("SELECT * FROM user WHERE " +
                        "username = ?")) {
                    s2.setString(1, toCheck);
                    try(ResultSet rs = s2.executeQuery()) {
    
                        if (rs.next()){
                            taken = true;
                        } else {
                            taken = false;
                        }
                    }
                
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
        
        return taken;
    }
    
    
}
