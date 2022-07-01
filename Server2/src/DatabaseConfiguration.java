import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    public String DatabaseName = "";

    public static String password = "rehab1125" ;

    public static Connection connection;

    public static void connectDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        String databaseName = "MarketDB";

        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        String username = "root";

        String password = DatabaseConfiguration.password;

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        connection = DriverManager.getConnection(url, username, password);

    }
    //com.mysql.cj.jdbc.Driver

}

