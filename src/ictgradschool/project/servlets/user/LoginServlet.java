package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.AdminDAO;
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
System.out.println(request.getParameter("articleID"));

        boolean passwordOK;

        passwordOK = UserDAO.checkPassword(username, password, getServletContext());
        if (passwordOK) {
            System.out.println("creating session");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            boolean adminOK;

            adminOK = AdminDAO.checkAdminStatus(username, getServletContext());
            if (adminOK) {
                System.out.println("creating session");
               session = request.getSession(true);
                session.setAttribute("admin", "admin");
            }

            if (request.getAttribute("new")!= null) {
                System.out.println("logged in new user, attemting redirect to profile edit");
                request.getRequestDispatcher("/profile").forward(request, response);
            } else {
               //redirects to the previous page if it was an article page
                if(request.getParameter("from").equals("/web-pages/single-article.jsp")){
                    request.setAttribute("articleID", request.getParameter("articleID"));
                    request.getRequestDispatcher("/article").forward(request,response);
                } else {
                    response.sendRedirect("/home");
                }
               // System.out.println(request.getParameter("from"));
                //response.sendRedirect(request.getParameter("/home"));
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
