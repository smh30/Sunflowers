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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean passwordOK;

        passwordOK = UserDAO.checkPassword(username, password, getServletContext());
        if (passwordOK) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            boolean adminOK;

            adminOK = AdminDAO.checkAdminStatus(username, getServletContext());
            if (adminOK) {
                //session = request.getSession(true);
                session.setAttribute("admin", "admin");
            }

            if (request.getAttribute("new") != null) {
                request.getRequestDispatcher("/profile").forward(request, response);
            } else {
                //redirects to the previous page if it was an article page
                if (request.getParameter("from").equals("ysy_SocialSunflowers/web-pages/single" +
                        "-article.jsp")) {
                    request.setAttribute("articleID", request.getParameter("articleID"));
                    request.getRequestDispatcher("article").forward(request, response);
                } else {
                    response.sendRedirect("home");
                }

            }

            return;
        } else {
            String message = "The username or password was incorrect";
            request.setAttribute("message", message);
            request.getRequestDispatcher("home").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
