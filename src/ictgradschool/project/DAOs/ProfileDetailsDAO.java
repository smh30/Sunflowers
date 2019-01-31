package ictgradschool.project.DAOs;


import ictgradschool.project.JavaBeans.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import javax.servlet.ServletContext;


public class ProfileDetailsDAO {

    public static User checkDetails(String username, String country, String realName, String desc, String dateOfBirth, String imageURL,ServletContext context) {
        Properties dbProps = new Properties();
        User user = new User();
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
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username= ?")) {
                stmt.setString(1, username);
                try (ResultSet r = stmt.executeQuery()) {
                    if (r.next()) {
                        //todo see if these are working
                        //
                        String USERNAME = r.getString(4);
                        String COUNTRY = r.getString(6);
                        String REALNAME= r.getString(7);
                        String DESC= r.getString(8);
                        String DOB= r.getString(3);
                        String IMAGEURL = r.getString(9);


                        user.setUsername(USERNAME);
                        user.setCountry(COUNTRY);
                        user.setRealName(REALNAME);
                        user.setDescription(DESC);
                        user.setDOB(DOB);
                        user.setPictureURL(IMAGEURL);
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
