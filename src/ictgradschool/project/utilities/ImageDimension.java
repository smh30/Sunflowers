package ictgradschool.project.utilities;

import java.awt.*;

public class ImageDimension {
    
    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;
        
//        // first check if we need to scale width
//        if (original_width > bound_width) {
//            //scale width to fit
//            new_width = bound_width;
//            //scale height to maintain aspect ratio
//            new_height = (new_width * original_height) / original_width;
//        }
//
//        // then check if we need to scale even with the new height
//        if (new_height > bound_height) {
//            //scale height to fit instead
//            new_height = bound_height;
//            //scale width to maintain aspect ratio
//            new_width = (new_height * original_width) / original_height;
//        }
        if (bound_width > original_width && bound_height > original_height) {
            if (original_height < original_width) {
                new_height = bound_height;
                new_width = (new_height * original_width) / original_height;
            } else {
                new_width = bound_width;
                new_height = (new_width * original_height) / original_width;
            }
            if (new_width > bound_width) {
                new_width = bound_width;
                new_height = (new_width * original_height) / original_width;
            }
            if (new_height > bound_height) {
                new_height = bound_height;
                new_width = (new_height * original_width) / original_height;
            }
        } else {
        
            // first check if we need to scale width
            if (original_width > bound_width) {
                // scale width to fit
                new_width = bound_width;
                // scale height to maintain aspect ratio
                new_height = (new_width * original_height) / original_width;
            }
        
            // then check if we need to scale even with the new height
            if (new_height > bound_height) {
                // scale height to fit instead
                new_height = bound_height;
                // scale width to maintain aspect ratio
                new_width = (new_height * original_width) / original_height;
            }
        }
        
        return new Dimension(new_width, new_height);
    }
}
