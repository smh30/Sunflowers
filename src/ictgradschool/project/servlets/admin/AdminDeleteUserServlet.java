package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.DAOs.ProfileDetailsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        boolean userDeleted = AdminDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            String message = "Hello Admin. There is some trouble with deleting the user's account. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("admininterface").forward(request, response);

        } else {

            response.sendRedirect("admininterface");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
