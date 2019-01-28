package ictgradschool.project.servlets;

import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    
    Properties dbProps;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the register servlet POST");
        dbProps = new Properties();
        
        /*In the Register servlet POST method, retrieve the username and password parameters
        supplied.*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        /*Connect to your database and from the table created in Exercise Five and check to see if
        a record with the specified username already exists in the table.*/
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        dbProps = new Properties();
    
        try (FileInputStream fIn = new FileInputStream(this.getServletContext().getRealPath("WEB-INF/mysql.properties"))) {
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
                    String message = "please choose a different username";
                    request.setAttribute("message", message);
                   
                    request.getRequestDispatcher("web-pages/register.jsp").forward(request,
                            response);
                
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
                    
        /*If it does not, hash the provided password in
        an appropriate fashion, then insert a new row into your database table including the
        new username, hashed password, salt and repetitions;*/
        /* todo: then redirect to the login servlet with the new user details so that they're
        logged in*/
         
                    request.getRequestDispatcher("login").forward(request, response);
                
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// if there was a get request, redirect to the blank form
        System.out.println("in the register servlet doget");
        request.getRequestDispatcher("web-pages/register.jsp").forward(request, response);
    }
}
