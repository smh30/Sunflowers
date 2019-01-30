package ictgradschool.project.servlets;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetSingleArticleServlet")
public class GetSingleArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the single article servlet");

        Article article = new Article();

        String currentUser = (String)(request.getSession().getAttribute("username"));
        // String author = request.getParameter("author");
        System.out.println("getting newest article by " + currentUser);

        article = ArticleDAO.getSingleArticle(currentUser, getServletContext());
        // get articles by that author



        List<Comment> comment = new ArrayList<Comment>();

       int articleId = article.getID();

        System.out.println("One new comment about the "+articleId+"article");

        comment = CommentDAO.getAllComments(articleId,getServletContext());

        request.setAttribute("comment",comment);



        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);




    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the single article doget");
        String param = (request.getParameter("id"));
        System.out.println("get param = " + param);
int articleID = Integer.parseInt(request.getParameter("id"));


        Article article = new Article();

        article = ArticleDAO.getArticleByID(articleID, getServletContext());
        // get articles by that author

        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);

    }
}
