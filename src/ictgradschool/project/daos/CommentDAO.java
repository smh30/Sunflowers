package ictgradschool.project.daos;

import ictgradschool.project.daos.checkproperties.DAOCheckProperties;
import ictgradschool.project.javabeans.Comment;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletContext;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommentDAO {
    public static List <Comment> getAllComments(int articleId, ServletContext context) {
        List <Comment> comments = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE " +
                        "article_id =? AND parent_comment IS NULL AND comments_author!='deleted' " +
                        "AND (hidden = false) ORDER BY comments_timestamp ")) {
                    stmt.setInt(1, articleId);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Comment comment = new Comment();
                        comment.setCommentID(rs.getInt(1));
                        User commentAuthor = new User(rs.getString(2));
                        comment.setCommentAuthor(commentAuthor);
                        comment.setCommentContent(rs.getString(3));
                        Timestamp raw =(rs.getTimestamp(4));
                        comment.setTimeString(ArticleDAO.getTimeString(raw));
                        comment.setArticleId(rs.getInt(5));
                        comments.add(comment);
                    }

                    List<Comment> preTailList;
                    List<Comment> tailList = new ArrayList<>(comments);

                    while(tailList.size()!=0) {
                        preTailList= new ArrayList<>(tailList);
                        for (Comment c : preTailList) {
                            getChildren(c, context);
                            if(c.getChildren()!=null)
                                tailList.addAll(c.getChildren());
                            tailList.remove(c);
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return comments;
        }
        return null;
    }



    public static void getChildren(Comment comment,ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE " +
                        "parent_comment = ? AND comments_author!='deleted' ORDER BY comments_timestamp ")) {
                    stmt.setInt(1, comment.getCommentID());

                    ResultSet rs = stmt.executeQuery();

                    List<Comment> children = new ArrayList<>();

                    while (rs.next()) {
                        Comment co = new Comment();
                        co.setCommentID(rs.getInt(1));
                        User commentAuthor = new User(rs.getString(2));
                        co.setCommentAuthor(commentAuthor);
                        co.setCommentContent(rs.getString(3));
                        Timestamp raw =(rs.getTimestamp(4));
                        co.setTimeString(ArticleDAO.getTimeString(raw));
                        co.setArticleId(rs.getInt(5));
                        children.add(co);
                    }

                    comment.setChildren(children);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean newComment(String content, String ArticleId, String user, ServletContext context) {

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                LocalDateTime a = LocalDateTime.now(ZoneId.of("Z"));
                Timestamp timestamp = Timestamp.valueOf(a);
                
                try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO comments(comments_author,article_id , coments_body, comments_timestamp)" +
                        "VALUES (?, ?, ?, ?)")) {
                    s2.setString(1, user);
                    s2.setString(2, ArticleId);
                    s2.setString(3, content);
                    s2.setTimestamp(4, timestamp);

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

    public static boolean deleteComment(int commentID, ServletContext context) {

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
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


    public static boolean addNestedComments(String parentid, String content, String ArticleId, String user, ServletContext context){
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
    
                LocalDateTime a = LocalDateTime.now(ZoneId.of("Z"));
                Timestamp timestamp = Timestamp.valueOf(a);

                try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO comments(comments_author,article_id , coments_body, comments_timestamp, parent_comment)" +
                        "VALUES (?, ?, ?, ?,?)")) {
                    s2.setString(1, user);
                    s2.setString(2, ArticleId);
                    s2.setString(3, content);
                    s2.setTimestamp(4, timestamp);
                    s2.setString(5,parentid);

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

}
