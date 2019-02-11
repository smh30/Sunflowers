package ictgradschool.project.javabeans;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String country;
    String realName;
    String description;
    String pictureURL;
    String defaultImage;
    String dateOfBirth;
    boolean useDefaultImage;
    String email;

    public User(){ }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) { this.realName = realName;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getDateOfBirth() { return dateOfBirth;}

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User (String username){
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public boolean isUseDefaultImage() {
        return useDefaultImage;
    }

    public void setUseDefaultImage(boolean useDefaultImage) {
        this.useDefaultImage = useDefaultImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
