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
//todo get the recent articles using dao
        //todo create a List<Article>
        //needs to include title, text, date/time, author...
        //todo send the list through to the home.jsp



        //todo delete this
        List<Article> articleList = new ArrayList<>();

        // if looking for all the articles ie no search parameter were entered....
        if((request.getParameter("author")==null)&&(request.getParameter("title")==null)&&(request.getParameter("date")==null)) {

            //articleList = ArticleDAO.getAllArticles(getServletContext());
            System.out.println("getting all articles");

            Article article = new Article();
            article.setTitle("Article 1");
            article.setArticleText("a manually created article for testing purposes");
            article.setTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            article.setAuthor(new User("harry"));
            articleList.add(article);
        } else {
            // if search parameter were entered or user wants to see their own articles...
            //todo search by other params (title/ date) and by combinations of params

            String author = request.getParameter("author");
            System.out.println("getting articles by " + author);

            articleList = ArticleDAO.getArticlesByAuthor(author, getServletContext());
            // get articles by that author
        }

        request.setAttribute("articles", articleList);

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
