package ictgradschool.project.servlets.user;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ictgradschool.project.daos.CustomProfilePicDAO;
import ictgradschool.project.utilities.ImageDimension;
import org.apache.commons.fileupload.FileItem;
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
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }
        //Create temp file for uploading
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
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

        try {
            List <FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImageFile = null;
            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    String fileName = fi.getName();
                    String[] split = fileName.split("\\\\");
                    String splited = split[split.length - 1];
                    fullsizeImageFile = new File(uploadsFolder, splited);
                    
                    fi.write(fullsizeImageFile);
                    
                    BufferedImage bimg = ImageIO.read(fullsizeImageFile);
                    Dimension imgSize = new Dimension(bimg.getWidth(), bimg.getHeight());
                    Dimension boundary = new Dimension(250, 250);
                    Dimension scaled = ImageDimension.getScaledDimension(imgSize, boundary);
                    
                    BufferedImage img = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
                    img.createGraphics().drawImage(ImageIO.read(fullsizeImageFile).getScaledInstance(scaled.width, scaled.height,
                            Image.SCALE_SMOOTH), 0, 0, null);
                    ImageIO.write(img, "jpg", fullsizeImageFile);
                    
                }
            }
            String image = fullsizeImageFile.getName();
            String user = (String) request.getSession().getAttribute("username");
            
            
            CustomProfilePicDAO.addImage(image, user, getServletContext());

            request.getRequestDispatcher("profile").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
