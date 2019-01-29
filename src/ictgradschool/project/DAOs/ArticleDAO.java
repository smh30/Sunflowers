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

public class ArticleDAO {

    public static List<Article> getAllArticles(ServletContext context) {
        List<Article> articles = new ArrayList<>();

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
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article ORDER BY article_timestamp DESC LIMIT 6")) {
                //stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){

                    //todo uncomment these once database is ready
                    Article article = new Article();
                    article.setTitle(rs.getString(1));
                    article.setArticleText(rs.getString(4));
                    article.setTimestamp(rs.getTimestamp(5));
                    User articleAuthor = new User(rs.getString(2));
                    article.setAuthor(articleAuthor);

                    articles.add(article);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static List<Article> getArticlesByAuthor(String author, ServletContext context){
        List<Article> articles = new ArrayList<>();
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
            // select the most recent 6 the articles table??? ordered by timestamp with certain author:
            //todo will this bring newest first or oldest first???
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM article AS a WHERE article_author = ? ORDER BY article_timestamp DESC LIMIT 6")) {
                stmt.setString(1, author);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){

                    //todo uncomment these once database is ready

                    //didn't get the id??
                    Article article = new Article();
                    article.setTitle(rs.getString(1));
                    article.setArticleText(rs.getString(4));
                    article.setTimestamp(rs.getTimestamp(5));
                    User articleAuthor = new User(rs.getString(2));
                    article.setAuthor(articleAuthor);
                    //todo another query or add to this one to return the comments as well, and create a list<comment>

                    articles.add(article);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
