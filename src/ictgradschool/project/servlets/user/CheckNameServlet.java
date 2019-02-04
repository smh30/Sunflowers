package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.UserDAO;

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
        String toCheck = req.getParameter("name");
        System.out.println("In Checking POST, name to check: " +toCheck);
        boolean taken = true;
        taken = UserDAO.isNameTaken(toCheck, getServletContext());
        System.out.println("is the username taken: " +taken);
    
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        if (taken){
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
        
        
    }
}
