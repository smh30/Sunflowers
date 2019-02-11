package ictgradschool.project.servlets.admin;

import ictgradschool.project.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminAddUserServlet")
public class AdminAddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean userCreated = UserDAO.newUser(username, password, getServletContext());
        if (!userCreated){
            String message = "There was an issue with adding the new user. The username may be " +
                    "taken already.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("admininterface").forward(request,
                    response);
        } else {

            String country = request.getParameter("country");
            String realName = request.getParameter("realname");
            String description = request.getParameter("description");
            String dateOfBirth = request.getParameter("dateofbirth");
            String email = request.getParameter("email");

            UserDAO.editUser(username, country, realName, description, dateOfBirth,
                    email, getServletContext());
            
            request.setAttribute("message", "New user " +username+ " successfully created!");
    
            request.getRequestDispatcher("admininterface").forward(request,
                    response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
