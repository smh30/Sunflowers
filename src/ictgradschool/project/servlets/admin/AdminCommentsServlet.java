package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.JavaBeans.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminCommentsServlet")
public class AdminCommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In Admin Comments Servlet");

        List<Comment> comments = new ArrayList<>();
        System.out.println(request.getParameter("articleID"));
        int articleID = Integer.parseInt(request.getParameter("articleID"));

        comments = AdminDAO.getAllComments (articleID, getServletContext());

        request.setAttribute("comments", comments);

        request.getRequestDispatcher("web-pages/admin-interface-comments.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
