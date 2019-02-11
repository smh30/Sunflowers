package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckNameServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toCheck = req.getParameter("name");
        boolean taken;
        taken = UserDAO.isNameTaken(toCheck, getServletContext());
    
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        if (taken){
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }
}
