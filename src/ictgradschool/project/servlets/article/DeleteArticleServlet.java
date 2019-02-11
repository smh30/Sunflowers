package ictgradschool.project.servlets.article;

import ictgradschool.project.daos.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("articleID"));

        boolean articleDeleted = ArticleDAO.deleteArticle(id, getServletContext());

        if (!articleDeleted) {
            String message = "Some trouble with deleting your article. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
