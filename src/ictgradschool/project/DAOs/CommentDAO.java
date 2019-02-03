package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.Comment;
import ictgradschool.project.JavaBeans.User;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommentDAO {
    public static List<Comment> getAllComments(int articleId, ServletContext context) {
        List<Comment> comments = new ArrayList<>();

        Properties dbProps = DAOCheckProperties.check(context);

        if(dbProps!=null) {

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");
            // select the most recent 6 from the articles table??? ordered by timestamp:
            //todo will this bring newest first or oldest first???
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE article_id =? ORDER BY comments_timestamp ")) {
                stmt.setInt(1, articleId);
                ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {

                    Comment comment = new Comment();
                    comment.setCommentContent(rs.getString(3));
                    comment.setCommentID(rs.getInt(1));

                    comment.setTimestamp(rs.getTimestamp(5));
                    User commentAuthor = new User(rs.getString(2));
                    comment.setCommentAuthor(commentAuthor);

                        comments.add(comment);
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return comments;
        }
        return null;
    }

    public static boolean newComment(String content,String ArticleId, String user, ServletContext context) {

        Properties dbProps = DAOCheckProperties.check(context);

        if(dbProps!=null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");


                try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO comments(comments_author,article_id , coments_body)" +
                        "VALUES (?, ?, ?)")) {
                    s2.setString(1, user);
                    s2.setString(2, ArticleId);

                    s2.setString(3, content);


                    s2.execute();


                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;

    }

    public static boolean deleteComment(String commentAuthor, String commentContent, int articleID, int commentID ,ServletContext context){

        Properties dbProps = DAOCheckProperties.check(context);

        if(dbProps!=null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful - delete comm dao");


                try (PreparedStatement s3 = conn.prepareStatement("UPDATE comments SET comments_author = ? WHERE comments_id = ?")) {

                    s3.setString(1, "deleted");
                    s3.setInt(2, commentID);

                    s3.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;

    }




}
