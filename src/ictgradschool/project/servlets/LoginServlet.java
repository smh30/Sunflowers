package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.UserDAO;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the login servlet doPost");


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("attempting login: " + username + " : " + password);   //successfully " +
        

        /*Connect to your database and from the table created in Exercise Five, retrieve the
        row identified by the username provided.*/

        boolean passwordOK;

        passwordOK = UserDAO.checkPassword(username, password, getServletContext());
        if (passwordOK) {
            System.out.println("creating session");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            //todo yet another place where i'm not sure what redirect to use
            if (request.getAttribute("new")!= null) {
                System.out.println("logged in new user, attemting redirect to profile edit");

                //todo change this to redirect to edit profile server once it's wired up
                request.getRequestDispatcher("web-pages/profile.jsp").forward(request, response);

            } else {

                System.out.println("logged in, attemting redirect to home");
                response.sendRedirect("home");
            }

            return;
        } else {

            System.out.println("password didn't match");
            String message = "The username or password was incorrect (password)";
            request.setAttribute("message", message);
            request.getRequestDispatcher("web-pages/login.jsp").forward(request, response);
            return;
        }


//request.getRequestDispatcher("home").forward(request, response); apparently doesn't work
        //response.sendRedirect("home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the login servlet doGet");
        request.getRequestDispatcher("web-pages/login.jsp").forward(request, response);
    }
}
