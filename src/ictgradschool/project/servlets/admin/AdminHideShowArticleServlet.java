package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowArticleServlet")
public class AdminHideShowArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In show and hide articles servlet");

        String action = request.getParameter("action");
        int articleId = Integer.parseInt(request.getParameter("articleID"));

        if (action.equals("show")) {
            boolean showArticle = AdminDAO.showArticle(articleId, getServletContext());
            if (!showArticle) {
                System.out.println("Whoops!");
                String message = "Hi Admin user! There is trouble with showing this article. Please try again.";
                request.setAttribute("message", message);
            }
        } else if (action.equals("hide")) {
            boolean hideArticle = AdminDAO.hideArticle(articleId, getServletContext());
            if (!hideArticle) {
                System.out.println("Whoops!");
                String message = "Hi Admin user! There is trouble with hiding this article. Please try again.";
                request.setAttribute("message", message);
            }
        }
        System.out.println("Woo! Got there");
//        request.getRequestDispatcher("admininterface").forward(request, response);
        response.sendRedirect("admininterface");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
