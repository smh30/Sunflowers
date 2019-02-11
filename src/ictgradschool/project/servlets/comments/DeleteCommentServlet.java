package ictgradschool.project.servlets.comments;

import ictgradschool.project.daos.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int articleID =  Integer.parseInt(request.getParameter("articleID"));

        int commentID = Integer.parseInt(request.getParameter("commentID"));

        boolean commentDeleted = CommentDAO.deleteComment(commentID ,getServletContext());

        if (!commentDeleted) {
            String message = "Some trouble with deleting your comment. Please try again.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("article").forward(request,response);
        }else{
            request.setAttribute("articleID", articleID);
            request.getRequestDispatcher("article").forward(request,response);
        }
    }
}