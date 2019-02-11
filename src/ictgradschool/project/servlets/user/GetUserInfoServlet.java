package ictgradschool.project.servlets.user;
import org.json.simple.JSONObject;

import ictgradschool.project.daos.UserDAO;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//this is very similar to the load profile servlet, but this one is for responding to ajax calls
// when a reader wants to see the info about the author

public class GetUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = UserDAO.getUserDetails(username, getServletContext());
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String image;
        if (user.getPictureURL() == null||user.isUseDefaultImage()){
            image = "images/default-photos-for-profile-page/" +user.getDefaultImage();
        } else {
            image = "Uploaded-Photos/"+user.getPictureURL();
        }
        JSONObject userJson = new JSONObject();
        
        userJson.put("username", user.getUsername());
        userJson.put("realname", user.getRealName());
        userJson.put("dob", user.getDateOfBirth());
        userJson.put("country", user.getCountry());
        userJson.put("bio", user.getDescription());
        userJson.put("image", image);
        
            response.getWriter().print(userJson);
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
