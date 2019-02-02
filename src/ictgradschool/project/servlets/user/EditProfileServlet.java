package ictgradschool.project.servlets.user;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.ProfileDetailsDAO;
import ictgradschool.project.DAOs.UserDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: if do post, actually filled in/change in form
        //get parameters in forms
        //Check if set to database variables or form variables
        System.out.println("Edit account.");
        String username = request.getParameter("username");
        String country = request.getParameter("country");
        String realName = request.getParameter("real_name");
        String description = request.getParameter("description");
        String dateOfBirth = request.getParameter("date_of_birth");
        String pictureURL = request.getParameter("image");

        User user = UserDAO.editUser(username, country, realName, description, dateOfBirth, pictureURL, getServletContext());

        request.setAttribute("user", user);

        request.setAttribute("username", username);
        request.getRequestDispatcher("/home").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //If do get, straight back to JSP
        //First opening page to view
        //Call DAO method to use details, return user id here
        //Here would request.setAttribute - set user onto it so send back to jsp - (profile) - forward req and res
//        request.setAttribute("user", user);
        System.out.println("edit get");
        User temp = UserDAO.getUserDetails(request.getParameter("username"), getServletContext());

        request.setAttribute("country", temp.getCountry());
        request.setAttribute("description", temp.getDescription());
        request.setAttribute("dateofbirth", temp.getDOB());
        request.setAttribute("image", temp.getPictureURL());
        request.setAttribute("realname", temp.getRealName());
        request.setAttribute("username", temp.getUsername());

        System.out.println("Got past setting attributes");

        request.getRequestDispatcher("web-pages/profile.jsp").forward(request,
                response);

    }
}
