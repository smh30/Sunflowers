package ictgradschool.project.servlets.user;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ictgradschool.project.DAOs.CustomProfilePicDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




@WebServlet(name = "UploadProfilePictureServlet")
public class UploadProfilePictureServlet extends HttpServlet {

    private File uploadsFolder;
    private File tempFolder;

    @Override
    public void init() throws ServletException {
        super.init();
        //Get the upload folder, ensure it exists
        this.uploadsFolder = new File(getServletContext().getRealPath("/Uploaded-Photos"));
        if(!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }
        //Create temp file for uploading
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if(!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        System.out.println("init() done");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("Print Writer set ");

        try{
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImageFile = null;
//
            for (FileItem fi: fileItems) {
                System.out.println("Reached FileItems list");
                if(!fi.isFormField()){
                    System.out.println("Reached if statement");
                    String fileName = fi.getName();
                    System.out.println(fileName);
                    System.out.println("Gotten file name");
                    String [] split = fileName.split("\\\\");
                    String splited = split[split.length -1];
                    fullsizeImageFile = new File(uploadsFolder, splited);
                    System.out.println("Creating new file");
                    System.out.println(fullsizeImageFile.toString());
                    fi.write(fullsizeImageFile);
                    System.out.println("Written to file");
                }
            }
//            out.println("<img src = ../Uploaded-Photos/" + fullsizeImageFile.getName()+ " " + "width\"200\">");
            System.out.println("Getting uploaded photo");
            String image = fullsizeImageFile.getName();
            String user = (String) request.getSession().getAttribute("username");
            CustomProfilePicDAO.addImage(image, user, getServletContext());
            request.getRequestDispatcher("/profile").forward(request, response);



        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}