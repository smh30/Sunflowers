package ictgradschool.project.servlets.article;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.SearchParams;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// this would be a get not a post
        //since no data is being submitted in order to go to the homepage
        System.out.println("in the home servlet post");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the home servlet");

        List <Article> articleList = new ArrayList <>();

        //first, get sort order
        String sort = "newest";
        if (request.getParameter("sort-options") != null) {
            System.out.println("Getting sort from request param");
            sort = request.getParameter("sort-options");
            request.getSession().setAttribute("sort", sort);
        } else if(request.getSession().getAttribute("sort")!=null){
            System.out.println("Getting sort from session attribute");
            sort = (request.getSession().getAttribute("sort")).toString();
        }

        // also, get offset. this part will get the curretly set offset if doing sort or w/e
        int offset = 0;
        if (request.getParameter("currentback") != null) {
            offset = Integer.parseInt(request.getParameter("currentback"));
            System.out.println("offset = " + offset);
        }
        if (request.getParameter("back")!=null){
            System.out.println("back=back ie clicked the back button");
            offset += 10;
        }
        if (request.getParameter("forward")!=null){
            System.out.println("forward was pressed");
            offset -= 10;
            if (offset<0){
                offset=0;
            }
        }

        // then, get search params
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String date = request.getParameter("date");

        System.out.println("in the home servlet, author parameter = " +author);

        String searchAuthor =null;
        String searchTitle =null;
        String searchDate =null;
        // if looking for all the articles ie no search parameter were entered....
        if ((author == null && title == null && date == null) ||(author.equals("") && title.equals("") && date.equals(""))) {
            System.out.println("getting all articles from offset " + offset);
            articleList = ArticleDAO.getAllArticles(offset, sort, getServletContext());


        } else if (author != null && title == null && date == null) {
            System.out.println("getting articles  by logged in user " + author);
            articleList = ArticleDAO.getArticlesByAuthor(offset, author, sort, getServletContext());

        } else {
            // if search parameter were entered or user wants to see their own articles...

            if (!author.equals("")) {
                searchAuthor = author;
                if (!title.equals("")) {
                    searchTitle = title;
                    if (!date.equals("")) {
                        searchDate = date;
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
            } else if (!title.equals("")) {
                searchTitle = title;
                System.out.println("getting articles by title:  " + title);
                articleList = ArticleDAO.getArticlesByTitle(offset, title, sort, getServletContext());
                if (!date.equals("")) {
                    searchDate = date;
                    System.out.println("getting articles by title and date: " + title + date);
                    articleList = ArticleDAO.getArticlesByTitleAndDate(offset, title, date, sort, getServletContext());
                }
            } else if (!date.equals("")) {
                searchDate = date;
                System.out.println("getting articles by date: " + date);
                articleList = ArticleDAO.getArticlesByDate(offset, date, sort, getServletContext());
            }
        }
//creating a list to hold the current params so they can pass back for the sake of sort etc
        SearchParams searchParams = new SearchParams();
        searchParams.setSearchAuthor(searchAuthor);
        searchParams.setSearchTitle(searchTitle);
        searchParams.setSearchDate(searchDate);


request.setAttribute("currentback", offset);
        request.setAttribute("searchParams", searchParams);
        request.setAttribute("currentsort", sort);
        request.setAttribute("articles", articleList);
        String message;
        if (request.getParameter("message")!=null){
            message=request.getParameter("message");
            request.setAttribute("message", message);
        }

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
