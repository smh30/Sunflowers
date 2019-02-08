package ictgradschool.project.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowCommentServlet")
public class AdminHideShowCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Find out if person hide/show comment
        //get parameters sent in
        //if/else
        //THEN:
        //once if/else done, DAO call...
        //hide = hidecomment
        //show = showcomment
        //In both DAOs, where comment id equal to x comment, set true/false where relevant
        //redirect back to admin-interface servlet!
        //So redoes loading info and sends back to admin-interface.jsp
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
