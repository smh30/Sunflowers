package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.JavaBeans.Article;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Article temp = ArticleDAO.getArticleByID(Integer.parseInt(request.getParameter("articleID")), getServletContext());

        request.setAttribute("title", temp.getTitle());
        request.setAttribute("content", temp.getArticleText());
        request.setAttribute("id", temp.getID());

        String content = (String) request.getAttribute("article-content");

        Article arti = ArticleDAO.editArticle(temp.getID(), temp.getTitle(), content, getServletContext());


            System.out.println("article edited!!!!!!!");

            request.setAttribute("article", arti);
            int id = arti.getID();

            request.setAttribute("articleID", id);
            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);

    }
}
