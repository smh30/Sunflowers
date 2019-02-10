package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String admin = request.getParameter("admin");

        boolean userDeleted = UserDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            if (admin.equals("admin")) {
                String message = "Hi Admin user! There is trouble with deleting this user's account. Please try again.";
                request.setAttribute("message", message);

                request.getRequestDispatcher("admininterface").forward(request, response);
            } else {
                String message = "Some trouble with deleting your profile. Please try again.";
                request.setAttribute("message", message);

                request.getRequestDispatcher("profile").forward(request, response);
            }
        } else {
            if (admin.equals("admin")) {
                response.sendRedirect("admininterface");
            } else {
                request.getSession().invalidate();
                response.sendRedirect("home");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
