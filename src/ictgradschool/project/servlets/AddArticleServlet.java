package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;

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
        System.out.println("The adding article servlet");

        String title = request.getParameter("article_title");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + title + "</h1>");

        String content = request.getParameter("article_content");
        PrintWriter out2 = response.getWriter();
        out2.println(content);

        String user = (String) request.getSession().getAttribute("username");

        boolean articeCreated = ArticleDAO.newArticle(title,content,user,getServletContext());

        if(!articeCreated){
            String message = "Some trouble with uploading your article. Please try again.";
            request.setAttribute("message",message);

            request.getRequestDispatcher("web-pages/new-article.jsp").forward(request,response);


        }else{
            // TODO to finish the single-article.jsp. It is the page after user submit their new article.  add attribute
            request.getRequestDispatcher("web-pages/single-article.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
