package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowCommentServlet")
public class AdminHideShowCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In show and hide comments servlet");

        String action = request.getParameter("action");
        int commentID= Integer.parseInt(request.getParameter("commentID"));

        if (action.equals("show")) {
            boolean showComment = AdminDAO.showComment(commentID, getServletContext());
            if (!showComment) {
                System.out.println("Whoops!");
                String message = "Hi Admin user! There is trouble with showing this comment. Please try again.";
                request.setAttribute("message", message);
            }
        } else if (action.equals("hide")) {
            boolean hideComment = AdminDAO.hideComment(commentID, getServletContext());
            if (!hideComment) {
                System.out.println("Whoops!");
                String message = "Hi Admin user! There is trouble with hiding this comment. Please try again.";
                request.setAttribute("message", message);
            }
        }
        System.out.println("Woo! Got there");
        request.getRequestDispatcher("admincomments").forward(request, response);
//        response.sendRedirect("adminhideshowcomment");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
