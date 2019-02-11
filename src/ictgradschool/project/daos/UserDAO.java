package ictgradschool.project.daos;

import ictgradschool.project.daos.checkproperties.DAOCheckProperties;
import ictgradschool.project.javabeans.User;
import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.Properties;

public class UserDAO {

    public static boolean checkPassword(String username, String password, ServletContext context) {

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                        /* If a matching row was found, hash the provided password with the retrieved salt and
        repetitions and compare it against the hash from the database.*/
                            int iterations = r.getInt(1);
                            byte[] salt = r.getBytes(2);
                            byte[] hash = r.getBytes(4);

                            if (Passwords.isExpectedPassword(password.toCharArray(), salt, iterations,
                                    hash)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
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
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();

                    //this will be true if data was returned, ie the user exists already
                    if (rs.isBeforeFirst()) {
                        /*If it does, redirect the client back to the ​register.html document.*/
                        return false;
                    } else {
                        byte[] salt = Passwords.getNextSalt(32);
                        byte[] hash = Passwords.hash(password.toCharArray(), salt);
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
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet r = stmt.executeQuery()) {
                        if (r.next()) {
                            user.setUsername(r.getString(3));
                            user.setCountry(r.getString(5));
                            user.setRealName(r.getString(6));
                            user.setDescription(r.getString(7));
                            user.setPictureURL(r.getString(8));
                            user.setDefaultImage(r.getString(9));
                            user.setDateOfBirth(r.getString(11));
                            user.setUseDefaultImage(r.getBoolean(12));
                            user.setEmail(r.getString(13));
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
                                String description, String dateOfBirth, String email, ServletContext context) {

        User user = new User();
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.user SET country = " +
                        "? ,real_name=?, description = ?, date_of_birth = ?, email = ? WHERE ysy" +
                        ".user" +
                        ".username= ?")) {
                    s2.setString(1, country);
                    s2.setString(2, realName);
                    s2.setString(3, description);
                    s2.setString(4, dateOfBirth);
                    s2.setString(5, email);
                    s2.setString(6, username);

                    s2.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.user SET default_image = ?, use_default_image = true WHERE " +
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

    public static boolean isNameTaken(String toCheck, ServletContext context) {
        boolean taken = true;

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s2 = conn.prepareStatement("SELECT * FROM user WHERE " +
                        "username = ?")) {
                    s2.setString(1, toCheck);
                    try (ResultSet rs = s2.executeQuery()) {

                        if (rs.next()) {
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

    public static boolean changePassword(String username, String password, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                byte[] salt = Passwords.getNextSalt(32);
                byte[] hash = Passwords.hash(password.toCharArray(), salt);
                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.user SET salt = ? ," +
                        "password=? WHERE ysy.user.username= " +
                        "?")) {
                    s2.setBytes(1, salt);
                    s2.setBytes(2, hash);
                    s2.setString(3, username);

                    s2.execute();
                }

                return true;


            } catch (
                    SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

    public static boolean deleteUser(String username, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

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