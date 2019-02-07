package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.UserDAO;
import ictgradschool.project.JavaBeans.User;
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
    

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the register servlet POST");

        /*In the Register servlet POST method, retrieve the username and password parameters
        supplied.*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        

        boolean userCreated = UserDAO.newUser(username, password, getServletContext());
        if (!userCreated){
            String message = "please choose a different username";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/register.jsp").forward(request,
                    response);
        } else {
request.setAttribute("new", true);
            request.getRequestDispatcher("login").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// if there was a get request, redirect to the blank form
        System.out.println("in the register servlet doget");
        //Think this is correct webpage - s
        request.getRequestDispatcher("web-pages/login.jsp").forward(request, response);
    }
}
