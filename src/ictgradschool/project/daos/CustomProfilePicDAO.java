package ictgradschool.project.daos;

import ictgradschool.project.daos.checkproperties.DAOCheckProperties;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.Properties;

public class CustomProfilePicDAO {

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
