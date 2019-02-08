package ictgradschool.project.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminHideShowArticleServlet")
public class AdminHideShowArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("In show and hide articles servlet");

    //Find out if person hide/show article
        //get parameters sent in
        //if/else
        //THEN:
        //once if/else done, DAO call...
        //hide = hidearticle
        //show = showarticle
        //In both DAOs, where article id equal to x article, set true/false where relevant
        //redirect back to admin-interface servlet!
        //So redoes loading info and sends back to admin-interface.jsp

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
