package ictgradschool.project.servlets;

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
//TODO: find library on web for these
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
        //TODO: add file path
        this.uploadsFolder = new File(getServletContext().getRealPath("/Final-Project"));
        if(!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }
        //Create temp file for uploading
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if(!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImageFile = null;

            for (FileItem fi: fileItems) {
                if(!fi.isFormField()&&(fi.getContentType().equals("image/jpeg")|| fi.getContentType().equals("image/png"))) {
                    String fileName = fi.getName();
                    fullsizeImageFile = new File(uploadsFolder, fileName);
                    fi.write(fullsizeImageFile);
                }
            }
            //TODO: Complete line below
            out.println("<img src = ");
        } catch (Exception e) {
            throw new ServletException(e);
        }
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
