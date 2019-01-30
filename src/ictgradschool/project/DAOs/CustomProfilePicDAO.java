package ictgradschool.project.DAOs;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CustomProfilePicDAO {

    public String checkImage(String profilePicURL, ServletContext context) {


        Properties dbProps = new Properties();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
            System.out.println("loaded properties");
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE profilePicURL= ?")) {
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
        //TODO: Check whether this is correct!!
        return null;
    }
}