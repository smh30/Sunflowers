package ictgradschool.project.servlets.article;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.CommentDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@WebServlet(name = "GetSingleArticleServlet")
public class GetSingleArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the single article servlet post");
        System.out.println("id param = " + request.getAttribute("articleID"));
        int articleID = 0;

        Article article = new Article();

        if (request.getAttribute("new") != null) {
            //if we have reached this page from creating a new article...
            System.out.println("came from a new article");
            articleID = Integer.parseInt(request.getAttribute("articleID").toString());


        } else {
            // if we have reached this page from clicking an article link in the main page or from adding a commnt
            System.out.println("came from a link");
            articleID = Integer.parseInt(request.getParameter("articleID").toString());
        }

        System.out.println("article id is = " + articleID);
        article = ArticleDAO.getArticleByID(articleID, getServletContext());
        // get articles by that author
        LocalDateTime a = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(a);
        System.out.println("currenttime: " + currentTime);
        Timestamp articleTime = Timestamp.valueOf(article.getTimeString());
        System.out.println("articletime: " + articleTime);

//        if ((article.getTimestamp()).after(currentTime)){
//            System.out.println("the article is postdated");
//            request.setAttribute("postdated", true);
//        }else{
//            System.out.println("a normal article");
//        }

        List <Comment> comments = new ArrayList <Comment>();

        int articleId = article.getID();

        comments = CommentDAO.getAllComments(articleId, getServletContext());


        request.setAttribute("comment", comments);


        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the single article doget");
        String param = (request.getParameter("articleID"));
        System.out.println("get param = " + param);
        int articleID = Integer.parseInt(request.getParameter("articleID"));


        Article article = new Article();

        article = ArticleDAO.getArticleByID(articleID, getServletContext());
        // get articles by that author
        List <Comment> comment = CommentDAO.getAllComments(articleID, getServletContext());
       

        request.setAttribute("comment", comment);

        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);

    }

}
