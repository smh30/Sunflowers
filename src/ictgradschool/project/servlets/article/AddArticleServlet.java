package ictgradschool.project.servlets.article;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.JavaBeans.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddArticleServlet")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post!");
        System.out.println("The adding article servlet");

        String title = request.getParameter("article_title");
        String content = request.getParameter("article_content");
        String date = request.getParameter("pub-date");
        String user = (String) request.getSession().getAttribute("username");

        Article newArticle = ArticleDAO.newArticle(title, content, user, date, getServletContext());
        System.out.println("provided date: " + date);


        newArticle = ArticleDAO.newArticle(title,content,user,date,getServletContext());

        if (newArticle == null) {
            System.out.println("article not created??");
            String message = "Some trouble with uploading your article. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);


        } else {
            System.out.println("article created!!!!!!!");
            request.setAttribute("new", true);
            request.setAttribute("article", newArticle);
            int id = newArticle.getID();
            System.out.println("new aartic's id" + id);
            request.setAttribute("articleID", id);
            // TODO to finish the single-article.jsp. It is the page after user submit their new article.  add attribute
            request.getRequestDispatcher("/article").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("newarticle", true);
        request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);

    }
}
