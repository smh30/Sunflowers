package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CustomProfilePicDAO {

    public String checkImage(String profilePicURL, ServletContext context) {


        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE image= ?")) {
                    stmt.setString(1, profilePicURL);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                            System.out.println("profilePicURL found" + r.getString(9));
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return profilePicURL;
        }
        return null;
    }

    public static boolean addImage(String image, String user, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s2 = conn.prepareStatement("UPDATE user SET image = ? , " +
                        "use_default_image = false " +
                        "WHERE username = ?")) {
                    s2.setString(1, image);
                    s2.setString(2, user);
                    s2.execute();


                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;

    }
}
