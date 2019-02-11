package ictgradschool.project.servlets.article;

import ictgradschool.project.daos.ArticleDAO;
import ictgradschool.project.daos.CommentDAO;
import ictgradschool.project.javabeans.Article;
import ictgradschool.project.javabeans.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetSingleArticleServlet")
public class GetSingleArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int articleID;

        Article article;

        if (request.getAttribute("new") != null) {
            articleID = Integer.parseInt(request.getAttribute("articleID").toString());
        } else {
            articleID = Integer.parseInt(request.getParameter("articleID"));
        }

        article = ArticleDAO.getArticleByID(articleID, getServletContext());
    
        LocalDateTime a = LocalDateTime.now(ZoneId.of("Pacific/Auckland"));
        Timestamp currentTime = Timestamp.valueOf(a);
        Timestamp articleTime = Timestamp.valueOf(article.getTimeString());

        if (articleTime.after(currentTime)){
            request.setAttribute("postdated", true);
        }

        List <Comment> comments;
        int articleId = article.getID();
        comments = CommentDAO.getAllComments(articleId, getServletContext());

        request.setAttribute("comments", comments);

        request.setAttribute("article", article);
        if (request.getAttribute("message")!=null){
            request.setAttribute("message", request.getAttribute("message"));
        }

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int articleID = Integer.parseInt(request.getParameter("articleID"));

        Article article;

        article = ArticleDAO.getArticleByID(articleID, getServletContext());
        List <Comment> comments = CommentDAO.getAllComments(articleID, getServletContext());

        request.setAttribute("comments", comments);
        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);

    }

}
