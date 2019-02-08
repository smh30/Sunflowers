package ictgradschool.project.DAOs;

import ictgradschool.project.DAOs.CheckProperties.DAOCheckProperties;
import ictgradschool.project.JavaBeans.Article;
import ictgradschool.project.JavaBeans.Comment;
import ictgradschool.project.JavaBeans.User;
import ictgradschool.project.utilities.Passwords;

import javax.servlet.ServletContext;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdminDAO {

    public static boolean checkAdminStatus(String username, ServletContext context) {
        //Checking external file of properties
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT ysy.user.admin FROM user WHERE ysy.user.username = ?")) {
                    stmt.setString(1, username);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            boolean admin = rs.getBoolean(1);
                            return admin;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List <User> getAllUsers(String username, ServletContext context) {
        List <User> users = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT username FROM user")) {

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        User user = new User();
                        user.setUsername(rs.getString(1));

                        users.add(user);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Change to different return type!!
        return users;
    }

    public static List <User> getAllUserPasswords(String username, String password, ServletContext context) {
        List <User> users = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                try (PreparedStatement stmt = conn.prepareStatement("SELECT username FROM user")) {

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        User user = new User();
                        user.setUsername(rs.getString(1));
                        //TODO: What do I do with password????
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static boolean deleteUser(String username, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");

                //TODO: Change placement re username to userID when linked up on database
                try (PreparedStatement s3 = conn.prepareStatement("DELETE FROM ysy.user WHERE username = ?")) {
                    System.out.println("working!!!");
                    s3.setString(1, username);

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

    public static List <Article> getAllArticles(ServletContext context) {
        List <Article> articles = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");


// yes, this sql contains a concatenated string, but it can only have the values returned by the method above, so it should be safe
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article WHERE NOT (article_author = 'deleted')")) {

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {

                        Article article = new Article();
                        article.setTitle(rs.getString(1));
                        User author = new User();
                        author.setUsername(rs.getString(2));
                        article.setAuthor(author);
                        article.setID(rs.getInt(3));
                        article.setHidden(rs.getBoolean(6));

                        articles.add(article);
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    public static List <Comment> getAllComments(int articleId, ServletContext context) {
        List <Comment> comments = new ArrayList <>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");
                //todo get the top-level comments (those without a parent)
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE " +
                        "article_id =? AND comments_author!='deleted' ORDER BY comments_timestamp ")) {
                    stmt.setInt(1, articleId);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Comment comment = new Comment();
                        comment.setCommentID(rs.getInt(1));
                        User commentAuthor = new User(rs.getString(2));
                        comment.setCommentContent(rs.getString(3));
                        comment.setTimestamp(rs.getTimestamp(4));
                        comment.setCommentAuthor(commentAuthor);
                        comment.setArticleId(rs.getInt(5));
                        comment.setParentID(rs.getInt(6));
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

    public static boolean hideArticle(int articleId, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.article SET hidden = true WHERE article_id = ?")) {
                    System.out.println("working!!!");
                    s3.setInt(1, articleId);

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

    public static boolean showArticle(int articleId, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                System.out.println("connection successful");

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.article SET hidden = false WHERE article_id = ?")) {
                    System.out.println("working!!!");
                    s3.setInt(1, articleId);

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