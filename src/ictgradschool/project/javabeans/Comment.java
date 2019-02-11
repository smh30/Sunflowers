package ictgradschool.project.javabeans;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {
    private String  commentContent;
    private User commentAuthor;
    private int articleId;
    private int commentID;
    private List<Comment> children;
    private Comment parent;
    private int parentID;
    private boolean hidden;
    private String timeString;

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
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

    public int getParentID() { return parentID; }

    public void setParentID(int parentID) { this.parentID = parentID; }

    public boolean isHidden() { return hidden; }

    public void setHidden(boolean hidden) { this.hidden = hidden; }
    
    public String getTimeString() {
        return timeString;
    }
    
    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
