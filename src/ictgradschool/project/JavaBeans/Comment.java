package ictgradschool.project.JavaBeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Comment implements Serializable {
    private String  commentContent;
    private User commentAuthor;
    private int articleId;
    private Timestamp timestamp;
    private int commentID;
    private List<Comment> children;

    private List Children;

    public List getChildren() {
        return Children;
    }

    public void setChildren(List children) {
        Children = children;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
