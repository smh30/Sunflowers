package ictgradschool.project.servlets.nestedComments;

import ictgradschool.project.DAOs.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNestedCommentServlet")
public class AddNestedCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The adding nested comment servlet");

        String commentID = request.getParameter("commentID");


        String content = request.getParameter("content");


        String user = (String) request.getSession().getAttribute("username");

//        List nestedcomment = CommentDAO.getChildren(commentID, )
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
