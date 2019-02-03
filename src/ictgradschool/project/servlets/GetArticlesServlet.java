package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.JavaBeans.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        
        //first, get sort order
        String sort = "newest";
        if(request.getParameter("sort-options")!=null){
            sort = request.getParameter("sort-options");
            System.out.println("sort order = " +sort);
        }
        
        // also, get offset. does this need a try/catch for numberformat exception??
        int offset = 0;
        if(request.getParameter("back")!=null){
            offset = Integer.parseInt(request.getParameter("back"));
            System.out.println("offset = " +offset);
        }
        
        // then, get search params
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        

//        System.out.println("author = " + author);
//        System.out.println("title = " + title);

        System.out.println("date = " + date);

        if (date != null) {
//        LocalDateTime a = LocalDateTime.now();
//        Timestamp timestamp = Timestamp.valueOf(a);
//        System.out.print(timestamp);
                   }



        // if looking for all the articles ie no search parameter were entered....
        if (author == null && title == null && date == null) {
            System.out.println("getting all articles from offset " + offset);
            articleList = ArticleDAO.getAllArticles(offset, sort, getServletContext());


        } else if(author!=null && title == null && date == null) {
            System.out.println("getting articles  by logged in user " + author);
            articleList = ArticleDAO.getArticlesByAuthor(offset, author, sort, getServletContext());

        }else  {
                // if search parameter were entered or user wants to see their own articles...
                //todo search by other params (title/ date) and by combinations of params
                if (!author.equals("")) {
                    if (!title.equals("")){
                        if (!date.equals("")){
                            System.out.println("getting articles by title, author and date: " + title + author + date);
                            articleList = ArticleDAO.getArticlesByAll(offset, title, author, date, sort, getServletContext());
                        } else {
                            System.out.println("getting articles by title and author :  " + title + author);
                            articleList = ArticleDAO.getArticlesByTitleAndAuthor(offset, title, author, sort, getServletContext());
                        }
                    } else {
                        System.out.println("getting articles by " + author);
                        articleList = ArticleDAO.getArticlesByAuthor(offset, author, sort, getServletContext());
                    }
                } else if (!title.equals("")){
                    System.out.println("getting articles by title:  " + title);
                    articleList = ArticleDAO.getArticlesByTitle(offset, title, sort, getServletContext());
                    if (!date.equals("")){
                        System.out.println("getting articles by title and date: " + title + date);
                        articleList = ArticleDAO.getArticlesByTitleAndDate(offset, title, date, sort, getServletContext());
                    }
                } else if (!date.equals("")){
                    System.out.println("getting articles by date: " + date);
                    articleList = ArticleDAO.getArticlesByDate(offset, date, sort, getServletContext());

                }
            }
request.setAttribute("currentsort", sort);
        request.setAttribute("articles", articleList);

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
