package ictgradschool.project.servlets.article;

import ictgradschool.project.daos.ArticleDAO;
import ictgradschool.project.javabeans.Article;
import ictgradschool.project.javabeans.SearchParams;

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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List <Article> articleList = new ArrayList <>();

        String sort = "newest";
        if (request.getParameter("sort-options") != null) {
            sort = request.getParameter("sort-options");
            request.getSession().setAttribute("sort", sort);
        } else if (request.getSession().getAttribute("sort") != null) {
            sort = (request.getSession().getAttribute("sort")).toString();
        }

        int offset = 0;
        if (request.getParameter("currentback") != null) {
            offset = Integer.parseInt(request.getParameter("currentback"));
        }
        if (request.getParameter("back") != null) {
            offset += 10;
        }
        if (request.getParameter("forward") != null) {
            offset -= 10;
            if (offset < 0) {
                offset = 0;
            }
        }

        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String date = request.getParameter("date");

        String searchAuthor = null;
        String searchTitle = null;
        String searchDate = null;

        if ((author == null && title == null && date == null) || (author.equals("") && title.equals("") && date.equals(""))) {
            articleList = ArticleDAO.getAllArticles(offset, sort, getServletContext());

        } else if (author != null && title == null && date == null) {
            searchAuthor = author;
            articleList = ArticleDAO.getArticlesByAuthor(offset, author, sort, getServletContext());

        } else {
            // if search parameter were entered or user wants to see their own articles...

            if (!author.equals("")) {
                searchAuthor = author;
                if (!title.equals("")) {
                    searchTitle = title;
                    if (!date.equals("")) {
                        searchDate = date;
                        articleList = ArticleDAO.getArticlesByAll(offset, title, author, date, sort, getServletContext());
                    } else {
                        articleList = ArticleDAO.getArticlesByTitleAndAuthor(offset, title, author, sort, getServletContext());
                    }
                } if (!date.equals("")){
                    searchDate = date;
                    articleList = ArticleDAO.getArticlesByAuthorAndDate(offset, author, date,
                            sort, getServletContext());
                } else {
                    articleList = ArticleDAO.getArticlesByAuthor(offset, author, sort, getServletContext());
                }
            } else if (!title.equals("")) {
                searchTitle = title;
                articleList = ArticleDAO.getArticlesByTitle(offset, title, sort, getServletContext());
                if (!date.equals("")) {
                    searchDate = date;
                    articleList = ArticleDAO.getArticlesByTitleAndDate(offset, title, date, sort, getServletContext());
                }
            } else if (!date.equals("")) {
                searchDate = date;
                articleList = ArticleDAO.getArticlesByDate(offset, date, sort, getServletContext());
            }
        }

        SearchParams searchParams = new SearchParams();
        searchParams.setSearchAuthor(searchAuthor);
        searchParams.setSearchTitle(searchTitle);
        searchParams.setSearchDate(searchDate);

        request.setAttribute("currentback", offset);
        request.setAttribute("searchParams", searchParams);
        request.setAttribute("currentsort", sort);
        request.setAttribute("articles", articleList);

        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            request.setAttribute("message", message);
        }

        request.getRequestDispatcher("web-pages/home.jsp").forward(request, response);
    }
}
