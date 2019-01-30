package ictgradschool.project.DAOs;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;


public class ProfileDetailsDAO {
    public String checkDetails(String username, int password, String country, String realName, String desc) {
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
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username= ? AND password = ? AND country=? AND real_name = ? AND description = ?")) {
                stmt.setString(1, username);
                stmt.setInt(2, password);
                stmt.setString(3, country);
                stmt.setString(4, realName);
                stmt.setString(5, desc);
                try (ResultSet r = stmt.executeQuery()) {
                    if (r.next()) {
                        //todo see if these are working
                        String USERNAME = r.getString(4);
                        int PASSWORD = r.getInt(5);
                        String COUNTRY = r.getString(6);
                        String REALNAME= r.getString(7);
                        String DESC= r.getString(8);
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
