package ictgradschool.project.servlets.article;

import ictgradschool.project.DAOs.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The deleting article servlet");

        String username = request.getParameter("author");

        String title = request.getParameter("title");

        String content = request.getParameter("articleText");

        int id = Integer.parseInt(request.getParameter("articleID"));


        String user = (String) request.getSession().getAttribute("username");

        boolean articeDeleted = ArticleDAO.deleteArticle(username, title, content, id, getServletContext());

        if (!articeDeleted) {
            String message = "Some trouble with deleting your article. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/single-article.jsp").forward(request,response);
            System.out.println("single");
        }else{

//            request.getRequestDispatcher("home").forward(request, response);
            response.sendRedirect("home");
            System.out.println("home");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
