import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class Table {
    protected final Configuration config = new Configuration();

    public boolean executeGeneralQuery(String query){
        try(
                Connection connect = config.connect();
                PreparedStatement ps = connect.prepareStatement(query)
        )
        {

            return ps.execute();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
