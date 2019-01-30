package ictgradschool.project.servlets;

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
        //Have look at over form
        //Send to database
        //Update DAO method

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //If do get, straight back to JSP
        //First opening page to view
        //Call DAO method to use details, return user id here
        //Here would request.setAttribute - set user onto it so send back to jsp - (profile) - forward req and res
        //In profile.jsp, have && etc EL user. ask Yun.
        //Set into form inputs - one called placeholder = $[user.realname} PASSWORD
        //value = if submit, value already was... Not great for updating!!
        //But if had value, would update every value in database every time
        //VALUE WINS
    }
}
