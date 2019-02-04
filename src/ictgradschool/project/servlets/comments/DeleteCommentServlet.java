package ictgradschool.project.servlets.comments;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post-delete comm dao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get-delete comm dao");
        String commentAuthor = (String) request.getSession().getAttribute("username");

        String commentContent = request.getParameter("commentContent");

        int articleID =  Integer.parseInt(request.getParameter("articleID"));

        int commentID = Integer.parseInt(request.getParameter("commentID"));

        boolean commentDeleted = CommentDAO.deleteComment(commentAuthor, commentContent, articleID, commentID ,getServletContext());

        if (!commentDeleted) {
            String message = "Some trouble with deleting your comment. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("article").forward(request,response);
        }else{
            request.getRequestDispatcher("article").forward(request,response);
        }
    }
}