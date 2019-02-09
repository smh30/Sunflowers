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

        String title = request.getParameter("article_title");
        String content = request.getParameter("article_content");
        String date = request.getParameter("pub-date");
        String user = (String) request.getSession().getAttribute("username");

        Article newArticle = ArticleDAO.newArticle(title, content, user, date, getServletContext());

        if (newArticle == null || newArticle.getID()==0) {

            String message = "An error occurred while uploading your article. Please try again.</p><p>Your title or article may have been too long";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);


        } else {
            request.setAttribute("new", true);
            request.setAttribute("article", newArticle);
            int id = newArticle.getID();
            request.setAttribute("articleID", id);

            request.getRequestDispatcher("/article").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("newarticle", true);
        request.getRequestDispatcher("web-pages/new-article.jsp").forward(request, response);

    }
}
