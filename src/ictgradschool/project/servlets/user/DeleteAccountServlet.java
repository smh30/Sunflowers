package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.ProfileDetailsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The deleting account servlet");

        //Line below left over from Yun's code - keep for now

        String username = (String) request.getSession().getAttribute("username");

        boolean userDeleted = ProfileDetailsDAO.deleteUser(username, getServletContext());

        if (!userDeleted) {
            String message = "There is some trouble with deleting your account. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/profile.jsp").forward(request,response);
            System.out.println("profile");

        }else{

            response.sendRedirect("home");
            System.out.println("home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
