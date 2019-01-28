package ictgradschool.project.servlets;

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

       // List<Article> articleList = ArticleDAO.getAllArticles(getServletContext());

        //todo delete this
        List<Article> articleList = new ArrayList<>();
        Article article = new Article();
        article.setTitle("Article 1");
        article.setArticleText("a manually created article for testing purposes");
        article.setTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        article.setAuthor(new User("harry"));
        articleList.add(article);

        request.setAttribute("articles", articleList);

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
