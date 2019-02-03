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


        //TODO: Check what on earth below line means
        String username1 = (String) request.getSession().getAttribute("username");

        boolean userDeleted = ProfileDetailsDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            String message = "Some trouble with deleting your profile. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/profile.jsp").forward(request,response);
            System.out.println("profile");
        }else{
            request.getSession().invalidate();
            response.sendRedirect("home");
            System.out.println("home");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}