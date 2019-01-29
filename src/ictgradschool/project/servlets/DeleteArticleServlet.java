package ictgradschool.project.servlets;

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

        String title = request.getParameter("title");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + title + "</h1>");

        String content = request.getParameter("articleText");
        PrintWriter out2 = response.getWriter();
        out2.println(content);

        String user = (String) request.getSession().getAttribute("username");

        boolean articeDeleted = ArticleDAO.deleteArticle(title, content,  getServletContext());

        if (!articeDeleted) {
            String message = "Some trouble with deleting your article. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("web-pages/single-article.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("web-pages/home.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
