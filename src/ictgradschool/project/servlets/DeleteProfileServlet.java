package ictgradschool.project.servlets;

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

        String country = request.getParameter("country");
        String description = request.getParameter("description");
        String dateofbirth = request.getParameter("dateofbirth");
        String image = request.getParameter("pictureURL");
        String realname = request.getParameter("realname");
        String username = request.getParameter("username");
        int userID = Integer.parseInt(request.getParameter("userID"));

        //TODO: Check what on earth below line means
        String user = (String) request.getSession().getAttribute("");

        boolean userDeleted = ProfileDetailsDAO.deleteUser(country, description, dateofbirth, image, realname, username, userID, getServletContext());

        if (!userDeleted) {
            String message = "Some trouble with deleting your profile. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/profile.jsp").forward(request,response);
            System.out.println("profile");
        }else{

//            request.getRequestDispatcher("home").forward(request, response);
            response.sendRedirect("home");
            System.out.println("home");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
