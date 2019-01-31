package ictgradschool.project.DAOs;

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

        Properties dbProps = new Properties();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");
            // select the most recent 6 from the articles table??? ordered by timestamp:
            //todo will this bring newest first or oldest first???
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ysy.comments WHERE article_id =? ORDER BY comments_timestamp ")) {
                stmt.setInt(1, articleId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    //todo uncomment these once database is ready
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

    public static boolean newComment(String content,String ArticleId, String user, ServletContext context) {

        Properties dbProps = new Properties();

        /*Connect to your database and from the table created in Exercise Five and check to see if
        a record with the specified username already exists in the table.*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");


            try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO ysy.comments(comments_author,article_id , coments_body)" +
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

    public static boolean deleteComment(String commentAuthor, String commentContent, int articleID, int commentID ,ServletContext context){

        Properties dbProps = new Properties();

        /*Connect to your database and from the table created in Exercise Five and check to see if
        a record with the specified username already exists in the table.*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("connection successful");


            try (PreparedStatement s3 = conn.prepareStatement("UPDATE comments SET comments_author = ? ,  WHERE article_id = ?, comments_id = ?")) {
                s3.setString(1, "c_deleted");
                s3.setInt(2, articleID);
                s3.setInt(3, commentID);

                System.out.println("maybe delete it ");

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




}
