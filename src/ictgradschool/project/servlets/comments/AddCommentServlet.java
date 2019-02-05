package ictgradschool.project.servlets.comments;

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

        String articleID = request.getParameter("articleID");

        String content = request.getParameter("comment");

        String user = (String) request.getSession().getAttribute("username");


        boolean commentAdded = CommentDAO.newComment(content,articleID,user,getServletContext());

        if(!commentAdded){
            System.out.println("comment not added??");
            String message = "Some trouble with adding your comment. Please try again.";
            request.setAttribute("message",message);

            request.getRequestDispatcher("article?articleID="+Integer.parseInt(articleID)).forward(request,response);



        }else{
            System.out.println("commetn created!!!!!!!");
            request.setAttribute("articleID", articleID);

            request.getRequestDispatcher("/article").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
