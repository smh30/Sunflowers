package ictgradschool.project.JavaBeans;

import java.sql.Timestamp;

public class Article {
    private String title;
    private User author;
    private String articleText;
    private Timestamp timePosted;
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

    public Timestamp getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Timestamp timePosted) {
        this.timePosted = timePosted;
    }
}
