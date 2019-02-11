package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");

        boolean userDeleted = UserDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            String message = "There is some trouble with deleting your account. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/profile.jsp").forward(request,response);

        }else{

            response.sendRedirect("home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
