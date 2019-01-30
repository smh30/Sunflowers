package ictgradschool.project.JavaBeans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private String  commentContent;
    private User commentAuthor;
    private Timestamp timestamp;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public User getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(User commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
