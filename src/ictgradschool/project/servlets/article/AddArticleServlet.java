package ictgradschool.project.servlets.article;

import ictgradschool.project.daos.ArticleDAO;
import ictgradschool.project.javabeans.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddArticleServlet")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("article_title");
        String content = request.getParameter("article_content");
        String date = request.getParameter("pub_date");
        String user = (String) request.getSession().getAttribute("username");

        Article newArticle = ArticleDAO.newArticle(title, content, user, date, getServletContext());

        if (newArticle == null || newArticle.getID() == 0) {

            String message = "An error occurred while uploading your article. Please try again.</p><p>Your title or article may have been too long";
            request.setAttribute("message", message);
            request.setAttribute("new_article", true);
            request.setAttribute("article_content", content);
            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);

        } else {
            request.setAttribute("new", true);
            request.setAttribute("article", newArticle);
            int id = newArticle.getID();
            request.setAttribute("articleID", id);

            request.getRequestDispatcher("article").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            request.setAttribute("message", "You do not have permission to access that page");
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            request.setAttribute("new_article", true);
            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);
        }
    }
}
