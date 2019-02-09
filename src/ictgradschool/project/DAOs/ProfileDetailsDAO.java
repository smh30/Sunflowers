package ictgradschool.project.DAOs;


import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import javax.servlet.ServletContext;


public class ProfileDetailsDAO {
    //TODO: Access if still need a lot of these parameters: most aren't active
    public static User checkDetails(String username, String country, String realName, String desc, String dateOfBirth, String imageURL, ServletContext context) {
        User user = new User();
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username= ?")) {
                    stmt.setString(1, username);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                            String USERNAME = r.getString(3);
                            String COUNTRY = r.getString(5);
                            String REALNAME = r.getString(6);
                            String DESC = r.getString(7);
                            String IMAGEURL = r.getString(8);
                            String DOB = r.getString(11);

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
        return null;
    }

    public static User getProfileByID(String username, ServletContext context) {
        User user = new User();
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ysy.user AS a WHERE username = ?")) {
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        String USERNAME = rs.getString(3);
                        String COUNTRY = rs.getString(5);
                        String REALNAME = rs.getString(6);
                        String DESCRIPTION = rs.getString(7);
                        String DOB = rs.getString(11);
                        String PICTUREURL = rs.getString(8);

                        user.setUsername(USERNAME);
                        user.setCountry(COUNTRY);
                        user.setRealName(REALNAME);
                        user.setDescription(DESCRIPTION);
                        user.setDOB(DOB);
                        user.setPictureURL(PICTUREURL);


                    }

                } catch (SQLException e) {
                    e.printStackTrace();

                }


            } catch (SQLException e) {
                e.printStackTrace();

            }


            return user;
        }
        return null;
    }

    public static boolean deleteUser(String username, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                //TODO: Change placement re username to userID when linked up on database
                try (PreparedStatement s3 = conn.prepareStatement("DELETE FROM ysy.user WHERE username = ?")) {
                    s3.setString(1, username);

                    s3.execute();

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
