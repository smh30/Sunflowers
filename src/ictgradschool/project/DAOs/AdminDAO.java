package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;
import ictgradschool.project.JavaBeans.User;
import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletContext;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdminDAO {

    public static boolean checkAdminStatus(String username, ServletContext context) {
        //Checking external file of properties
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT ysy.user.admin FROM user WHERE ysy.user.username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            boolean admin = rs.getBoolean(1);
                            return admin;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List <User> getAllUsers (String username, ServletContext context) {
        List <User> users = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT username FROM user")) {

                    ResultSet rs = stmt.executeQuery();

                    while(rs.next()) {
                        User user = new User();
                        user.setUsername(rs.getString(1));

                        users.add(user);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Change to different return type!!
       return users;
    }

}