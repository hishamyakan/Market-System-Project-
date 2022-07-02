
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    private static final String uri = "jdbc:sqlite:";
    private static final String defaultDB = "Market.db";

    public Connection connect() throws SQLException {
        return connectTo(defaultDB);
    }
    public Connection connectTo(String DBName) throws SQLException {
        return DriverManager.getConnection(uri+DBName);
    }
}
