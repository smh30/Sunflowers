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

        //the user which will be returned to populate the form
        User user;
        String username = request.getSession().getAttribute("username").toString();
        System.out.println("username from session:  "+username);
        
        //if they're trying to change their default image
        if (request.getParameter("default-img") !=null){

            //do a DAO to set the users preferred default
            String chosenImg = request.getParameter("default-img");

            if (chosenImg.equals("custom")){
                // do nothing
                System.out.println("chose to remain with custom image");
            }else {
                System.out.println("changing default image");
                UserDAO.changeDefaultImage(chosenImg, username, getServletContext());
            }
            
            
        } else {
            System.out.println("Edit account details");
            String country = request.getParameter("country");
            String realName = request.getParameter("realname");
            String description = request.getParameter("description");
            String dateOfBirth = request.getParameter("dateofbirth");
            String email = request.getParameter("email");
            
            System.out.print(username);
            System.out.println(realName);
    
            UserDAO.editUser(username, country, realName, description, dateOfBirth,
             email, getServletContext());
        }
        //request.setAttribute("user", user);

        //request.setAttribute("username", username);
//        request.getRequestDispatcher("home").forward(request, response);
        response.sendRedirect("/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //If do get, straight back to JSP
        //First opening page to view
        //Call DAO method to use details, return user id here
        //Here would request.setAttribute - set user onto it so send back to jsp - (profile) - forward req and res
//        request.setAttribute("user", user);
        //todo figure out if we ever actually end up in this part
        System.out.println("edit get ?????????????????????????????");
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
