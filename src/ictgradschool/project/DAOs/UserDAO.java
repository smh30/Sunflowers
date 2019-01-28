package ictgradschool.project.DAOs;

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
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM loginTable WHERE username = ?")) {
                stmt.setString(1, username);
                try (ResultSet r = stmt.executeQuery()) {
                    if (r.next()) {
                        System.out.println("username found! " + r.getString(1));
                        /* If a matching row was found, hash the provided password with the retrieved salt and
        repetitions and compare it against the hash from the database.*/
                        // first column is name, already have
                        //todo see if these are working
                        // second column is binary hash, what data type to save it as??
                        byte[] hash = r.getBytes(2);
                        // third column is salt, also binary
                        byte[] salt = r.getBytes(3);
                        int iterations = r.getInt(4);


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

    public static boolean newUser(String username, String password, ServletContext context){

        Properties dbProps = new Properties();

        /*Connect to your database and from the table created in Exercise Five and check to see if
        a record with the specified username already exists in the table.*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM loginTable WHERE username = ?")) {
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
                    try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO loginTable " +
                            "VALUES (?, ?, ?, ?)")){
                        s2.setString(1, username);
                        s2.setBytes(2, hash);
                        s2.setBytes(3, salt);
                        s2.setInt(4, 100000);
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
}
