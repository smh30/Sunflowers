package ictgradschool.project.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String helloWorld = "Hello, World!";

        req.setAttribute("helloWorld", helloWorld);

        req.getRequestDispatcher("/WEB-INF/hello-world-view.jsp").forward(req, resp);

    }
}
