package ictgradschool.project.servlets;

import ictgradschool.project.DAOs.ArticleDAO;
import ictgradschool.project.DAOs.ProfileDetailsDAO;
import ictgradschool.project.DAOs.UserDAO;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.User;

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

        String username = request.getParameter("username");
        String country = request.getParameter("country");
        String realName = request.getParameter("real_name");
        String description = request.getParameter("description");
        String dateOfBirth = request.getParameter("date_of_birth");
        String pictureURL = request.getParameter("image");

        //Have look at over form

        //TODO: What are we putting here after the DAO am calling on??? Does it need to be new??
        //Okay, we need to send password, but REALLY DON"T WANT TO STORE ANYWHERE BUT EXTERNAL FILE
        //TODO: Do properties file update automatically? Do we need to store it there.
//        boolean editingDetails = ProfileDetailsDAO.checkDetails(username,country,realName, description, dateOfBirth, pictureURL, getServletContext());
//        if (!editingDetails){
//            String message = "Please enter updated account details";
//            request.setAttribute("message", message);
//
//            request.getRequestDispatcher("web-pages/profile.jsp").forward(request,
//                    response);
//        } else {
//            request.setAttribute("new", true);
//            request.getRequestDispatcher("profile").forward(request, response);
//        }
//        //Send to database
//
//        //Update DAO method
//
//        request.getRequestDispatcher("/profile").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //If do get, straight back to JSP
        //First opening page to view
        //Call DAO method to use details, return user id here
        //Here would request.setAttribute - set user onto it so send back to jsp - (profile) - forward req and res
//        request.setAttribute("user", user);
        User temp = UserDAO.getUserDetails(request.getParameter("username"), getServletContext());

        request.setAttribute("country", temp.getCountry());
        request.setAttribute("description", temp.getDescription());
        request.setAttribute("dateofbirth", temp.getDOB());
        request.setAttribute("image", temp.getPictureURL());
        request.setAttribute("realname", temp.getRealName());
        request.setAttribute("username", temp.getUsername());
        request.setAttribute("userID", temp.getUserID());



        String country = request.getParameter("country");
        String description = request.getParameter("description");
        String dateofbirth = request.getParameter("dateofbirth");
        String image = request.getParameter("pictureURL");
        String realname = request.getParameter("realname");
        String username = request.getParameter("username");
        int userID = Integer.parseInt(request.getParameter("userID"));

        User user = UserDAO.editUser(temp.getCountry(), temp.getDescription(), temp.getDOB(), temp.getPictureURL(), temp.getRealName(), temp.getUsername(),temp.getUserID(), getServletContext());

        System.out.println("user edited!!!!!!!");

        request.setAttribute("user", user);
        String get = user.getUsername();

        request.setAttribute("user", user);

        request.getRequestDispatcher("web-pages/profile.jsp").forward(request,
                response);

    }
}
