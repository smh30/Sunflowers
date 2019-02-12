package ictgradschool.project.daos;

import ictgradschool.project.daos.checkproperties.DAOCheckProperties;
import ictgradschool.project.javabeans.Article;
import ictgradschool.project.javabeans.User;

import javax.servlet.ServletContext;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArticleDAO {
    
    public static List<Article> getAllArticles(int offset, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                
                String orderBy = getOrderString(sort);
                
                LocalDateTime a = LocalDateTime.now(ZoneId.of("UTC"));
                Timestamp currentTime = Timestamp.valueOf(a);


// yes, this sql contains a concatenated string, but it can only have the values returned by the method above, so it should be safe
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article WHERE (hidden=0)" +
                        "AND NOT (article_author = 'deleted') AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 " +
                        "OFFSET ?")) {
                    
                    stmt.setTimestamp(1, currentTime);
                    stmt.setInt(2, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                    
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
    private static Article createArticleFromRS(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setTitle(rs.getString(1));
        User articleAuthor = new User(rs.getString(2));
        article.setAuthor(articleAuthor);
        article.setID(rs.getInt(3));
        article.setArticleText(rs.getString(4));
        Timestamp rawTimestamp = (rs.getTimestamp(5));
        article.setTimeString(getTimeString(rawTimestamp));
        
        return article;
    }
    
    private static String getOrderString(String sort) {
        String orderBy;
        
        switch (sort) {
            case "newest":
                orderBy = " article_timestamp DESC ";
                break;
            case "oldest":
                orderBy = " article_timestamp ASC ";
                break;
            case "author-a":
                orderBy = " article_author ASC ";
                break;
            case "author-z":
                orderBy = " article_author DESC ";
                break;
            case "title-a":
                orderBy = " article_title ASC ";
                break;
            case "title-z":
                orderBy = " article_title DESC ";
                break;
            default:
                orderBy = " article_timestamp DESC ";
                break;
        }
        return orderBy;
    }
    
    public static List<Article> getArticlesByAuthor(int offset, String author, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a WHERE article_author LIKE ?  AND NOT (article_timestamp > ?) AND (hidden=0) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    
                    stmt.setString(1, author);
                    stmt.setString(2, todaysDate);
                    stmt.setInt(3, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
    public static Article newArticle(String title, String content, String user, String date, ServletContext context) {
        
        Article article = new Article();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                Timestamp timestamp;
                if (!date.equals("")) {
                    timestamp = Timestamp.valueOf(date + " 00:00:00");
                } else {
                    LocalDateTime a = LocalDateTime.now(ZoneId.of("Z"));
                    timestamp = Timestamp.valueOf(a);
                }
                
                try (PreparedStatement s2 = conn.prepareStatement("INSERT INTO article(article_title,article_author , article_body, article_timestamp)" +
                        "VALUES (?, ?, ?, ?)")) {
                    s2.setString(1, title);
                    s2.setString(2, user);
                    s2.setString(3, content);
                    s2.setTimestamp(4, timestamp);
                    s2.execute();
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    return article;
                }
                
                try (PreparedStatement s3 = conn.prepareStatement("SELECT article_id FROM article" +
                        " WHERE article_author = ? AND article_title = ? AND article_body = ? AND" +
                        " NOT " +
                        "(article_timestamp > ?)  " +
                        "ORDER " +
                        "BY article_timestamp DESC LIMIT 1")) {
                    s3.setString(1, user);
                    s3.setString(2, title);
                    s3.setString(3, content);
                    s3.setTimestamp(4, timestamp);
                    try (ResultSet rs = s3.executeQuery()) {
                        if (rs.next()) {
                            article.setID(rs.getInt(1));
                        }
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return article;
        }
        return null;
    }
    
    
    public static boolean deleteArticle(int id, ServletContext context) {
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                
                try (PreparedStatement s3 = conn.prepareStatement("UPDATE article SET article_author = ? WHERE article_id = ?")) {
                    s3.setString(1, "deleted");
                    s3.setInt(2, id);
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
    
    public static List<Article> getArticlesByTitle(int offset, String title, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a WHERE article_title LIKE ? AND NOT (article_author = 'deleted')   AND (hidden=0) AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    stmt.setString(1, "%" + title + "%");
                    stmt.setString(2, todaysDate);
                    stmt.setInt(3, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
    public static List<Article> getArticlesByTitleAndAuthor(int offset, String title, String author, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a WHERE article_title LIKE ? AND article_author LIKE ? AND NOT (article_author = 'deleted')  AND (hidden=0)  AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    stmt.setString(1, "%" + title + "%");
                    stmt.setString(2, author);
                    stmt.setString(3, todaysDate);
                    stmt.setInt(4, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                    
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
    public static Article getArticleByID(int id, ServletContext context) {
        Article article = new Article();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a WHERE article_id = ?")) {
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        article.setTitle(rs.getString(1));
                        User articleAuthor = new User(rs.getString(2));
                        article.setAuthor(articleAuthor);
                        article.setID(rs.getInt(3));
                        article.setArticleText(rs.getString(4));
                        Timestamp raw = (rs.getTimestamp(5));
                        article.setTimeString(getTimeString(raw));
                        
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
            return article;
        }
        return null;
    }
    
    public static String getTimeString(Timestamp raw) {
        String rstring = (raw.toString().replace(' ', 'T').substring(0, 19)) + "Z" +
                "[UTC]";
        ZonedDateTime utcTime = ZonedDateTime.parse(rstring);
        ZonedDateTime nzTime = utcTime.withZoneSameInstant(ZoneId.of("Pacific" +
                "/Auckland"));
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(nzTime);
    }
    
    public static Article editArticle(int id, String title, String content, ServletContext context) {
        Article article = new Article();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                try (PreparedStatement s2 = conn.prepareStatement("UPDATE ysy.article SET article_title =?, article_body = ? WHERE ysy.article.article_id = ?")) {
                    s2.setString(1, title);
                    s2.setString(2, content);
                    s2.setInt(3, id);
                    
                    s2.execute();
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                return article;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return article;
        }
        return null;
    }
    
    public static List<Article> getArticlesByDate(int offset, String date, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                System.out.println("date = " + date);
                
                LocalDateTime midnightToday = LocalDateTime.parse(date+" 00:00:00", DateTimeFormatter.ofPattern("yyyy" +
                        "-MM-dd HH:mm:ss"));
                Instant midnightInstant = midnightToday.toInstant(ZoneOffset.of("+13:00"));
                LocalDateTime midnightUtc = LocalDateTime.ofInstant(midnightInstant, ZoneId.of("Z"));
                String midnightString = midnightUtc.toString();
                midnightString = midnightString.substring(0, 10);
                
                String start = midnightString + " 11:00:00";
                String day = midnightString.substring(8, 10);
                int dayint = Integer.parseInt(day);
                int nextday = dayint + 1;
                String end = midnightString.substring(0,8) + nextday + " 11:00:00";
                System.out.println("start = " +start);
                System.out.println("end  =" + end);
                
                
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
                
//
                    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a " +
                            "WHERE article_timestamp BETWEEN ? AND " +
                            "? AND NOT " +
                            "(article_author =" +
                            " " +
                            "'deleted')  " +
                            "AND " +
                            "(hidden=0)  AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                        stmt.setString(1, start);
                        stmt.setString(2, end);
                    stmt.setString(3, todaysDate);
                    stmt.setInt(4, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return articles;
        }
        return null;
    }
    
    public static List<Article> getArticlesByAll(int offset, String author, String title, String date, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
    
                LocalDateTime midnightToday = LocalDateTime.parse(date+" 00:00:00", DateTimeFormatter.ofPattern("yyyy" +
                        "-MM-dd HH:mm:ss"));
                Instant midnightInstant = midnightToday.toInstant(ZoneOffset.of("+13:00"));
                LocalDateTime midnightUtc = LocalDateTime.ofInstant(midnightInstant, ZoneId.of("Z"));
                String midnightString = midnightUtc.toString();
                midnightString = midnightString.substring(0, 10);
    
                String start = midnightString + " 11:00:00";
                String day = midnightString.substring(8, 10);
                int dayint = Integer.parseInt(day);
                int nextday = dayint + 1;
                String end = midnightString.substring(0,8) + nextday + " 11:00:00";
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a " +
                        "WHERE article_author LIKE ? AND article_title LIKE ? AND " +
                        "article_timestamp BETwEEN ? AND ?  AND (hidden=0) AND NOT " +
                        "(article_author = " +
                        "'deleted')  AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    stmt.setString(1, author);
                    stmt.setString(2, "%" + title + "%");
                    stmt.setString(3, start);
                    stmt.setString(4, end);
                    stmt.setString(5, todaysDate);
                    stmt.setInt(6, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return articles;
        }
        return null;
    }
    
    public static List<Article> getArticlesByTitleAndDate(int offset, String title, String date, String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
    
                LocalDateTime midnightToday = LocalDateTime.parse(date+" 00:00:00", DateTimeFormatter.ofPattern("yyyy" +
                        "-MM-dd HH:mm:ss"));
                Instant midnightInstant = midnightToday.toInstant(ZoneOffset.of("+13:00"));
                LocalDateTime midnightUtc = LocalDateTime.ofInstant(midnightInstant, ZoneId.of("Z"));
                String midnightString = midnightUtc.toString();
                midnightString = midnightString.substring(0, 10);
    
                String start = midnightString + " 11:00:00";
                String day = midnightString.substring(8, 10);
                int dayint = Integer.parseInt(day);
                int nextday = dayint + 1;
                String end = midnightString.substring(0,8) + nextday + " 11:00:00";
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a " +
                        "WHERE article_title LIKE ? AND article_timestamp BETWEEN ? AND ? AND NOT" +
                        " " +
                        "(article_author = 'deleted')  AND (hidden=0)  AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    stmt.setString(1, "%" + title + "%");
                    stmt.setString(2, start);
                    stmt.setString(3, end);
                    stmt.setString(4, todaysDate);
                    stmt.setInt(5, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
    public static List<Article> getArticlesByAuthorAndDate(int offset, String author, String date,
                                                      String sort, ServletContext context) {
        List<Article> articles = new ArrayList<>();
        Properties dbProps = DAOCheckProperties.check(context);
        
        if (dbProps != null) {
            
            try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
                String orderBy = getOrderString(sort);
                String todaysDate = Timestamp.valueOf(LocalDateTime.now()).toString();
                
                LocalDateTime midnightToday = LocalDateTime.parse(date+" 00:00:00", DateTimeFormatter.ofPattern("yyyy" +
                        "-MM-dd HH:mm:ss"));
                Instant midnightInstant = midnightToday.toInstant(ZoneOffset.of("+13:00"));
                LocalDateTime midnightUtc = LocalDateTime.ofInstant(midnightInstant, ZoneId.of("Z"));
                String midnightString = midnightUtc.toString();
                midnightString = midnightString.substring(0, 10);
                
                String start = midnightString + " 11:00:00";
                String day = midnightString.substring(8, 10);
                int dayint = Integer.parseInt(day);
                int nextday = dayint + 1;
                String end = midnightString.substring(0,8) + nextday + " 11:00:00";
                
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a " +
                        "WHERE article_author LIKE ? AND article_timestamp BETWEEN ? AND ? AND NOT" +
                        " " +
                        "(article_author = 'deleted')  AND (hidden=0)  AND NOT (article_timestamp > ?) ORDER BY" + orderBy + "LIMIT 10 OFFSET ?")) {
                    stmt.setString(1, author);
                    stmt.setString(2, start);
                    stmt.setString(3, end);
                    stmt.setString(4, todaysDate);
                    stmt.setInt(5, offset);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Article article = createArticleFromRS(rs);
                        articles.add(article);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articles;
        }
        return null;
    }
    
}
