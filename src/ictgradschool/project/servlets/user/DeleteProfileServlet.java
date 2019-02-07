package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.ProfileDetailsDAO;
import ictgradschool.project.DAOs.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("The deleting profile servlet");

        String username = request.getParameter("username");
        String admin = request.getParameter("admin");

        boolean userDeleted = ProfileDetailsDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            if (admin.equals("admin")) {
                System.out.println("Whoops!");
                String message = "Hi Admin user! There is trouble with deleting this user's account. Please try again.";
                request.setAttribute("message", message);

                request.getRequestDispatcher("admininterface").forward(request, response);
            } else {
                System.out.println("Ouch!");
                String message = "Some trouble with deleting your profile. Please try again.";
                request.setAttribute("message", message);

                request.getRequestDispatcher("profile").forward(request, response);
                System.out.println("profile");
            }
        } else {
            if (admin.equals("admin")) {
                System.out.println("Am going to admin interface");
//                request.getRequestDispatcher("admininterface").forward(request, response);
                response.sendRedirect("admininterface");
            } else {
                System.out.println("Going home!");
                request.getSession().invalidate();
                response.sendRedirect("home");
                System.out.println("home");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
