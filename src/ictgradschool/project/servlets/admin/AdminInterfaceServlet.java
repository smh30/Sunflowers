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
import java.util.ArrayList;
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



        List <User> userList = new ArrayList <>();

        //Here do an alphabetical String sort

        //What is an offset??

        //Search param time!
        String username1 = request.getParameter("username");


        userList = AdminDAO.getAllUsers(username, getServletContext());
        System.out.println("Attempting processing: " + username);

        request.setAttribute("users", userList);

        request.getRequestDispatcher("web-pages/admin-interface.jsp").forward(request, response);


        //TODO: Check whether
        List<User> userPasswordList = new ArrayList <>();

        //Think they are using parameters from above page??

        userPasswordList = AdminDAO.getAllUserPasswords(username, password, getServletContext());
        System.out.println("Attempting processing getAllUsersPasswords");

        request.setAttribute("users", userPasswordList);

        request.getRequestDispatcher("web-pages/admin-interface.jsp").forward(request, response);
    }
}

