package ictgradschool.project.servlets.admin;

import ictgradschool.project.daos.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowArticleServlet")
public class AdminHideShowArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        int articleId = Integer.parseInt(request.getParameter("articleID"));

        if (action.equals("show")) {
            boolean showArticle = AdminDAO.showArticle(articleId, getServletContext());
            if (!showArticle) {
                String message = "Hi Admin user! There is trouble with showing this article. Please try again.";
                request.setAttribute("message", message);
            }
        } else if (action.equals("hide")) {
            boolean hideArticle = AdminDAO.hideArticle(articleId, getServletContext());
            if (!hideArticle) {
                String message = "Hi Admin user! There is trouble with hiding this article. Please try again.";
                request.setAttribute("message", message);
            }
        }
        response.sendRedirect("admininterface");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
