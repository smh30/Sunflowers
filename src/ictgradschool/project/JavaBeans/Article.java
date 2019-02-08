package ictgradschool.project.JavaBeans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article implements Serializable {
    private String title;
    private User author;
    private String articleText;
    private Timestamp timestamp;
    private int ID;
    private boolean hidden;
    // this one will depend on how we implement the comments
    //todo getters and setters for comments once
    //private List<Comment> comments;

    public Article(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isHidden() { return hidden; }

    public void setHidden(boolean hidden) { this.hidden = hidden; }

    public void setAuthor(String string) {
    }
}
