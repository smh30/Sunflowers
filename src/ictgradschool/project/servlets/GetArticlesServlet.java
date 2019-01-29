package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "GetArticlesServlet")
public class GetArticlesServlet extends HttpServlet {
    // this is basically to create the homepage
    // should it be renamed 'homepageservlet??'
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// this would be a get not a post
        //since no data is being submitted in order to go to the homepage
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the home servlet");

        List<Article> articleList = new ArrayList<>();

        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String date = request.getParameter("date");

        System.out.println("author = " + author);
        System.out.println("title = " + title);
        System.out.println("date = " + date);

        // if looking for all the articles ie no search parameter were entered....
        if (author == null && title == null && date == null) {

            //articleList = ArticleDAO.getAllArticles(getServletContext());
            System.out.println("getting all articles");

            articleList = ArticleDAO.getAllArticles(getServletContext());


        } else {
            // if search parameter were entered or user wants to see their own articles...
            //todo search by other params (title/ date) and by combinations of params
            if (!author.equals("")) {
                author = request.getParameter("author");
                System.out.println("getting articles by " + author);
                articleList = ArticleDAO.getArticlesByAuthor(author, getServletContext());
                // get articles by that author
            }

           // if (title != "")
        }

        request.setAttribute("articles", articleList);

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
