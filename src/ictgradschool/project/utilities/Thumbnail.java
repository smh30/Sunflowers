package ictgradschool.project.utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Thumbnail {
    
    public static void createThumbnail(File imageFile, File outputDirectory) throws IOException {
        BufferedImage img = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(ImageIO.read(imageFile).getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
        
        File thumbnailFile = new File(outputDirectory.getCanonicalPath() + File.separator + imageFile.getName());
        ImageIO.write(img, "jpg", thumbnailFile);
        
        ImageIcon displayableImage = new ImageIcon();
        displayableImage.setImage(img);
        return;
    }
}
