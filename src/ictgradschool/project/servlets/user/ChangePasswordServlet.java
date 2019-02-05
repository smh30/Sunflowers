package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.DAOs.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the changePW servlet doPost");
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        
        boolean oldPasswordOK;
        String msg = "";
        
        oldPasswordOK = UserDAO.checkPassword(username, oldPassword, getServletContext());
        if (oldPasswordOK) {
            System.out.println("old password was correct");
            boolean pwChanged = UserDAO.changePassword(username, newPassword, getServletContext());
            
            if (pwChanged){
                System.out.println("password was changed");
                msg = "Your password was changed successfully";
            } else {
                System.out.println("pw not changed for some reason");
                msg = "Your password was not changed. Please try again.";
            }
            
        } else {
            
            msg = "Your password was not changed. Please try again.";
        }
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/profile").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the changePw servlet doGet - shouldn't have ended up here");
    }


}
