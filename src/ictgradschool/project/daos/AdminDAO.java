package ictgradschool.project.daos;

import ictgradschool.project.daos.checkproperties.DAOCheckProperties;
import ictgradschool.project.javabeans.Article;
import ictgradschool.project.javabeans.Comment;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdminDAO {

    public static boolean checkAdminStatus(String username, ServletContext context) {

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
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

    public static List<User> getAllUsers(ServletContext context) {
        List<User> users = new ArrayList<>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
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
        return users;
    }


    public static List<Article> getAllArticles(ServletContext context) {
        List<Article> articles = new ArrayList<>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

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

    public static List<Comment> getAllComments(int articleId, ServletContext context) {
        List<Comment> comments = new ArrayList<>();

        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE " +
                        "article_id =? AND comments_author!='deleted' ORDER BY comments_timestamp ")) {
                    stmt.setInt(1, articleId);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Comment comment = new Comment();
                        comment.setCommentID(rs.getInt(1));
                        User commentAuthor = new User(rs.getString(2));
                        comment.setCommentContent(rs.getString(3));
                        comment.setCommentAuthor(commentAuthor);
                        comment.setArticleId(rs.getInt(5));
                        comment.setParentID(rs.getInt(6));
                        comment.setHidden(rs.getBoolean(7));
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

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.article SET hidden = true WHERE article_id = ?")) {
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

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.article SET hidden = false WHERE article_id = ?")) {
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

    public static boolean hideComment(int commentId, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.comments SET hidden = true WHERE comments_id = ?")) {
                    s3.setInt(1, commentId);
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

    public static boolean showComment(int commentID, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);

        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s3 = conn.prepareStatement("UPDATE ysy.comments SET hidden = false WHERE comments_id = ?")) {
                    s3.setInt(1, commentID);
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

    public static String getUserEmail(String username, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);
        String email = "";
        if (dbProps != null) {

            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {

                try (PreparedStatement s3 = conn.prepareStatement("SELECT ysy.user.email FROM ysy.user WHERE username =?")) {
                    s3.setString(1, username);
                    ResultSet rs = s3.executeQuery();

                    if (rs.next()) {
                        email = rs.getString(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return email;
    }
}