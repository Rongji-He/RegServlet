import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static BasicDataSource ds;
    static {
        Properties p=new Properties();
        InputStream is= DBUtils.class.getClassLoader()
                .getResourceAsStream("../resources/jdbc.properties");
        try{
            p.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        String driver= p.getProperty("driver");
        String url= p.getProperty("url");
        String username= p.getProperty("username");
        String password= p.getProperty("password");

        ds=new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(3);
        ds.setMaxActive(5);
        ds.setMaxIdle(3);
    }

    public static Connection getConn() throws SQLException{
        return ds.getConnection();
    }
}
