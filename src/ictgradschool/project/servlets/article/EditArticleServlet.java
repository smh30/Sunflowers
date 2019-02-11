package ictgradschool.project.servlets.article;

import ictgradschool.project.daos.ArticleDAO;
import ictgradschool.project.javabeans.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("article_title");
        String content = request.getParameter("article_content");
        String stringID = request.getParameter("articleID");
        int articleID = Integer.parseInt(stringID);
        Article article = ArticleDAO.editArticle(articleID, title, content, getServletContext());

        request.setAttribute("article", article);

        request.setAttribute("articleID", articleID);
        request.getRequestDispatcher("article").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article temp = ArticleDAO.getArticleByID(Integer.parseInt(request.getParameter("articleID")), getServletContext());

        request.setAttribute("title", temp.getTitle());
        request.setAttribute("content", temp.getArticleText());
        request.setAttribute("id", temp.getID());
        request.setAttribute("article", temp);

        request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);


    }
}
