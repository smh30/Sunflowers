package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.UserDAO;

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
            String message = "please choose a different username";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/register.jsp").forward(request,
                    response);
        } else {

            System.out.println("Process other account details");

            String country = request.getParameter("country");
            String realName = request.getParameter("realname");
            String description = request.getParameter("description");
            String dateOfBirth = request.getParameter("dateofbirth");
            String email = request.getParameter("email");

            System.out.print(username);
            System.out.println(realName);

            UserDAO.editUser(username, country, realName, description, dateOfBirth,
                    email, getServletContext());

            response.sendRedirect("/admininterface");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
