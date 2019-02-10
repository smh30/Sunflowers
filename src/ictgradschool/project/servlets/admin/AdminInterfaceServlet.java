package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.DAOs.CommentDAO;
import ictgradschool.project.DAOs.ProfileDetailsDAO;
import ictgradschool.project.DAOs.UserDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.Comment;
import ictgradschool.project.JavaBeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        String username = request.getParameter("username");

        List <User> userList = new ArrayList <>();


        userList = AdminDAO.getAllUsers(username, getServletContext());
        System.out.println("Attempting processing: " + username);

        request.setAttribute("users", userList);


        List <User> userPasswordList = new ArrayList <>();

        List <Article> articles = new ArrayList <>();

        String title = request.getParameter("title");
        String author = request.getParameter("author");

        articles = AdminDAO.getAllArticles(getServletContext());

        request.setAttribute("articles", articles);

        request.getRequestDispatcher("web-pages/admin-interface.jsp").forward(request,response);
    }
}

