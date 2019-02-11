package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        
        boolean oldPasswordOK;
        String msg;
        
        oldPasswordOK = UserDAO.checkPassword(username, oldPassword, getServletContext());
        if (oldPasswordOK) {
            boolean pwChanged = UserDAO.changePassword(username, newPassword, getServletContext());
            
            if (pwChanged){
                msg = "Your password was changed successfully";
            } else {
                msg = "Your password was not changed. Please try again.";
            }
            
        } else {
            
            msg = "Your password was not changed. Please try again.";
        }
        request.setAttribute("message", msg);
        request.getRequestDispatcher("profile").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
