package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.Properties;

public class AdminDAO {

    public static boolean checkAdminStatus(String username, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT ysy.user.admin FROM user WHERE ysy.user.username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            //get admin variable boolean OR int
                            //so need to make JB???? Steph says no
//                    if (rs.get=true){
//                        return true;
//                    } else if(admin=false) {
//                        return false;
//                    }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //TODO: Figure out WHY/IF this is false
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }return false;
    }
}