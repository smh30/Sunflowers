package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.JavaBeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminInterfaceServlet")
public class AdminInterfaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the admin interface servlet do post");
        }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        System.out.println("in the admin interface servlet do get");


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean admin = Boolean.valueOf(request.getParameter("admin"));
        System.out.println("Attempting processing: " + username + password + admin);


        //TODO: Ask Steph what new equals????? Want this to be true OR false
        System.out.println("logged in admin user, attemting redirect to admin page");

        List<User> users;

        users = AdminDAO.adminTable(username, getServletContext());


        request.getRequestDispatcher("web-pages/admin-interface.jsp").forward(request, response);


//    }

    }
}

