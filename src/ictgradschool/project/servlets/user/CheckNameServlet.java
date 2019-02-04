package ictgradschool.project.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckNameServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("checking servlet GET");
    }
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("checking servlet POST");
        System.out.println("In Checking POST, name to check: " + req.getParameter("name"));
        
    }
}
