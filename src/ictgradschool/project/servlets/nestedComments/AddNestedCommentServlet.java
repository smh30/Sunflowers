package ictgradschool.project.servlets.nestedComments;

import ictgradschool.project.daos.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNestedCommentServlet")
public class AddNestedCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String articleID = request.getParameter("articleID");

        String content = request.getParameter("content");

        String user = (String) request.getSession().getAttribute("username");

        String parentID = request.getParameter("commentID");

        boolean addNestedComment = CommentDAO.addNestedComments(parentID,content,articleID,user,getServletContext());

        if(!addNestedComment){
            String message = "Your comment was not added. It may have been too long.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("article?articleID="+Integer.parseInt(articleID)).forward(request,response);
        }else{
            request.setAttribute("articleID", articleID);
            request.getRequestDispatcher("article").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
