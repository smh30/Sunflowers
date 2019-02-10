package ictgradschool.project.servlets.admin;

import ictgradschool.project.daos.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowCommentServlet")
public class AdminHideShowCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int commentID= Integer.parseInt(request.getParameter("commentID"));

        if (action.equals("show")) {
            boolean showComment = AdminDAO.showComment(commentID, getServletContext());
            if (!showComment) {
                String message = "Hi Admin user! There is trouble with showing this comment. Please try again.";
                request.setAttribute("message", message);
            }
        } else if (action.equals("hide")) {
            boolean hideComment = AdminDAO.hideComment(commentID, getServletContext());
            if (!hideComment) {
                String message = "Hi Admin user! There is trouble with hiding this comment. Please try again.";
                request.setAttribute("message", message);
            }
        }
        request.getRequestDispatcher("admincomments").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
