package ictgradschool.project.servlets.user;

import ictgradschool.project.daos.UserDAO;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getSession().getAttribute("username").toString();

        //if they're trying to change their default image
        if (request.getParameter("default-img") != null) {

            String chosenImg = request.getParameter("default-img");

            if (!chosenImg.equals("custom")) {
                UserDAO.changeDefaultImage(chosenImg, username, getServletContext());
            }

        } else {
            String country = request.getParameter("country");
            String realName = request.getParameter("realname");
            String description = request.getParameter("description");
            String dateOfBirth = request.getParameter("dateofbirth");
            String email = request.getParameter("email");


            UserDAO.editUser(username, country, realName, description, dateOfBirth,
                    email, getServletContext());
        }
        response.sendRedirect("profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User temp = UserDAO.getUserDetails(request.getParameter("username"), getServletContext());

        request.setAttribute("country", temp.getCountry());
        request.setAttribute("description", temp.getDescription());
        request.setAttribute("dateofbirth", temp.getDateOfBirth());
        request.setAttribute("image", temp.getPictureURL());
        request.setAttribute("realname", temp.getRealName());
        request.setAttribute("username", temp.getUsername());


        request.getRequestDispatcher("web-pages/profile.jsp").forward(request, response);

    }
}
