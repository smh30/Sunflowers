package ictgradschool.project.servlets;

import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    
    Properties dbProps;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("in the login servlet doPost");

dbProps = new Properties();
    
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("attempting login: " + username + " : " + password);   //successfully " +
        

        /*Connect to your database and from the table created in Exercise Five, retrieve the
        row identified by the username provided.*/
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        try (FileInputStream fIn = new FileInputStream(this.getServletContext().getRealPath("WEB-INF/mysql.properties"))) {
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
                            /* If the password did match, create a new Session and add an attribute
        recording the username of your logged in user, then redirect the client to the
        question06.html document.*/
                        System.out.println("creating session");
                            HttpSession session = request.getSession(true);
                            session.setAttribute("username", username);
                            //todo yet another place where i'm not sure what redirect to use
                        
                            System.out.println("logged in, attemting redirect to home");
                            response.sendRedirect("home");
                            //request.getRequestDispatcher("home").forward(request,response);
                        return;
                        
                        } else {
                        
                        /* If they do not match, again
        print an error message to the HTML document indication that the username or password
        was incorrect.*/
                        
                            //todo implement this bit once seeing how the one below should look
                            System.out.println("password didn't match");
                            String message = "The username or password was incorrect (password)";
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("web-pages/login.jsp").forward(request,response);
                            return;
                        }
                    
                    
                    } else {

                         /* If there is not a matching row, print an error
        message to the HTML document indication that the username or password was incorrect.*/
                        //System.out.println("username or password was incorrectttt");
                        String message = "The username or password was incorrect (username)";
                    
                        request.setAttribute("message", message);
                    
                    
                        //todo see which of these is correct
                        //todo how to add a message sayint the user/pw we3re incorrect??
                        //doGet(request, response);
                        //or
                        request.getRequestDispatcher("web-pages/login.jsp").forward(request,
                                response);
                        //or
                    return;
                        //response.sendRedirect("login.jsp");
                    
                    }
                
                }
            }
        
        
        } catch (SQLException e) {
            e.printStackTrace();
        }




//request.getRequestDispatcher("home").forward(request, response); apparently doesn't work
        response.sendRedirect("home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("in the login servlet doGet");
request.getRequestDispatcher("web-pages/login.jsp").forward(request, response);
    }
}
