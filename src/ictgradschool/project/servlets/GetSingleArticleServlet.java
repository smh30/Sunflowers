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
        System.out.println("in the single article servlet post");
        System.out.println("id param = " + request.getAttribute("articleID"));
        int articleID= 0;

        Article article = new Article();

        if(request.getAttribute("new")!=null){
            //if we have reached this page from creating a new article...
            System.out.println("came from a new article");
            articleID = Integer.parseInt(request.getAttribute("articleID").toString());


        } else {
            // if we have reached this page from clicking an article link in the main page or from adding a commnt
            System.out.println("came from a link");
            articleID = Integer.parseInt(request.getAttribute("articleID").toString());
        }

        System.out.println("article id is = " + articleID);
        article = ArticleDAO.getSingleArticle(articleID, getServletContext());
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
        String param = (request.getParameter("articleID"));
        System.out.println("get param = " + param);
int articleID = Integer.parseInt(request.getParameter("articleID"));


        Article article = new Article();

        article = ArticleDAO.getArticleByID(articleID, getServletContext());
        // get articles by that author

        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);

    }
}
