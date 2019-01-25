package ictgradschool.project.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    
    Properties dbProps;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// if there was a get request, redirect to the blank form
        request.getRequestDispatcher("web-pages/register.jsp").forward(request, response);
    }
}
