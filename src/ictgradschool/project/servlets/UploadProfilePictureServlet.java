package ictgradschool.project.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UploadProfilePictureServlet")
public class UploadProfilePictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In the Upload Profile Picture Servlet, doPost");

        //TODO: Methods inside

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String fullPhotoPath = servletContext.getRealPath("/default-photos-for-profile-page");

        File pFolder = new File(fullPhotoPath);
        File[] photoList = pFolder.listFiles();

        if (photoList.length < 1) {
            System.out.println("No photos stored in folder.");
        } else {
            for (File aPhotoList : photoList) {
                   //TODO: Go through and print out photos onto page?????
            }
        }

        System.out.println("In the Upload Profile Picture Servlet, doGet");
        request.getRequestDispatcher("webpages/profile.jsp").forward(request, response);
    }
}
