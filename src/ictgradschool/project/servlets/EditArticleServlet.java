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
        System.out.println("edit post");
        String title = request.getParameter("article_title");
        String content = request.getParameter("article_content");
        String stringID = request.getParameter("articleID");
        System.out.println("title = " +title);
        System.out.println("content = " +content);
        System.out.println("id needs to get here:" + stringID);
        int articleID = Integer.parseInt(stringID);
        Article arti = ArticleDAO.editArticle(articleID, title, content, getServletContext());
    
    
        System.out.println("article edited!!!!!!!");
    
        request.setAttribute("article", arti);
        
    
        request.setAttribute("articleID", articleID);
        request.getRequestDispatcher("/article").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("edit get");
        Article temp = ArticleDAO.getArticleByID(Integer.parseInt(request.getParameter("articleID")), getServletContext());

        System.out.println("temp article id: " + temp.getID());
        request.setAttribute("title", temp.getTitle());
        request.setAttribute("content", temp.getArticleText());
        request.setAttribute("id", temp.getID());
        request.setAttribute("article", temp);
        System.out.println("title to be returned for editing: " + temp.getTitle());
        
        request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);


    }
}
