package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoadProfileServlet")
public class LoadProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if(username == null){
            request.setAttribute("message", "You do not have permission to access that page");
            request.getRequestDispatcher("home").forward(request,response);
        } else {
            User user = UserDAO.getUserDetails(username, getServletContext());
            request.setAttribute("user", user);
            request.getRequestDispatcher("web-pages/profile.jsp").forward(request, response);
        }
    }
}
