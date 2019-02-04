package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminInterfaceServlet")
public class AdminInterfaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the admin interface servlet do post");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //TODO: Check if TINYINT and boolean are compatible
        Boolean admin = Boolean.valueOf(request.getParameter("admin"));
        System.out.println("Attempting processing: " + username + password + admin);

        boolean adminOK;

        adminOK = AdminDAO.checkAdminStatus(username, password, admin, getServletContext());
        if (adminOK) {
            //TODO: Ask Steph if I need to make a session
            System.out.println("creating session");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            //TODO: Do I need to set this Attribute to admin somewhere?????
        }
        if (request.getAttribute("new") != null) {
            System.out.println("logged in admin user, attemting redirect to admin page");
            request.getRequestDispatcher("/admininterface").forward(request, response);
        } else {

        }
    }

        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            System.out.println("in the admin interface servlet do get");

        }
    }

