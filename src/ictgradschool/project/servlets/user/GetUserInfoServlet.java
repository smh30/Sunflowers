package ictgradschool.project.servlets.user;
import org.json.simple.JSONObject;

import ictgradschool.project.DAOs.UserDAO;
import ictgradschool.project.JavaBeans.User;

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
        System.out.println(" in servlet search for: " + username);
        User user = UserDAO.getUserDetails(username, getServletContext());
    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("wher is it getting stuck??");
        System.out.println("pictureURL from DAO: " + user.getPictureURL());
        System.out.println("use default value: " + user.isUseDefaultImage());
        System.out.println("default url: " +user.getDefaultImage());
        String image = "";
        if (user.getPictureURL() == null||user.isUseDefaultImage()){
            System.out.println("use the default image");
            image = "../default-photos-for-profile-page/" +user.getDefaultImage();
        } else {
            System.out.println("use custom image");
            image = "../Uploaded-Photos/"+user.getPictureURL();
        }
        System.out.println(username +"s picture is " + image);
        JSONObject userJson = new JSONObject();
    
        // Adds a String value to the JSON
        userJson.put("username", user.getUsername());
        userJson.put("realname", user.getRealName());
        userJson.put("dob", user.getDOB());
        userJson.put("country", user.getCountry());
        userJson.put("bio", user.getDescription());
        userJson.put("image", image);
        
    
        //put only the relevant image, the entire url of it
        
            response.getWriter().print(userJson);
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("user ajax servlet get");
    }
}
