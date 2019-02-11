package ictgradschool.project.daos.checkproperties;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOCheckProperties {
    public static Properties check(ServletContext context) {
        Properties dbProps = new Properties();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream(context.getRealPath("WEB-INF/mysql.properties"))) {
            dbProps.load(fIn);
        } catch (IOException e) {
            System.out.println("couldn't find the properties file???????");
            e.printStackTrace();
            return null;
        }

        return dbProps;
    }
}
