package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The adding comment servlet");

        String ArticleId = request.getParameter("articleID");


        String content = request.getParameter("comment");


        String user = (String) request.getSession().getAttribute("username");

        boolean commentAdded = CommentDAO.newComment(content,ArticleId,user,getServletContext());

        if(!commentAdded){
            System.out.println("comment not added??");
            String message = "Some trouble with adding your comment. Please try again.";
            request.setAttribute("message",message);

            request.getRequestDispatcher("article?id="+Integer.parseInt(ArticleId)).forward(request,response);



        }else{
            System.out.println("article created!!!!!!!");
            // TODO to finish the single-article.jsp. It is the page after user submit their new article.  add attribute
            request.getRequestDispatcher("/article").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
