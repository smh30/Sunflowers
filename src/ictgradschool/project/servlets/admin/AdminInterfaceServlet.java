package ictgradschool.project.servlets.admin;

import ictgradschool.project.daos.AdminDAO;
import ictgradschool.project.javabeans.Article;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminInterfaceServlet")
public class AdminInterfaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        if (request.getSession().getAttribute("admin") == null) {
            request.setAttribute("message", "You do not have permission to access that page");
            request.getRequestDispatcher("home").forward(request, response);
        } else {

            List<User> userList;

            userList = AdminDAO.getAllUsers(getServletContext());

            request.setAttribute("users", userList);


            List<Article> articles;

            articles = AdminDAO.getAllArticles(getServletContext());

            request.setAttribute("articles", articles);

            request.getRequestDispatcher("web-pages/admin-interface.jsp").forward(request, response);
        }
    }
}

