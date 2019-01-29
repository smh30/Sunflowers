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

@WebServlet(name = "GetSingleArticleServlet")
public class GetSingleArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the single article servlet");

        Article article = new Article();

        String currentUser = (String)(request.getSession().getAttribute("username"));
       // String author = request.getParameter("author");
        System.out.println("getting newest article by " + currentUser);

        article = ArticleDAO.getSingleArticle(currentUser, getServletContext());
        // get articles by that author


        request.setAttribute("article", article);

        request.getRequestDispatcher("web-pages/single-article.jsp").forward(request, response);
    }

}
