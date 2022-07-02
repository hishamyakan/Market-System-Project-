import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryTable {

    public static ArrayList<category> Category_report() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        DatabaseConfiguration.connectDB();

        String query = "SELECT CATEGORY , SUM(QUANTITY) , SUM(NO_OF_SOLD_ITEMS)  FROM ITEM GROUP BY CATEGORY";

        PreparedStatement ps = DatabaseConfiguration.connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ArrayList<category> a = new ArrayList<category>();

        while (rs.next()) {
            category c = new category();
            c.setName(rs.getString(1));
            c.setQuantity(rs.getInt(2));
            c.setNo_of_sold_items(rs.getInt(3));
            a.add(c);
        }

        return a;
    }

    public static ArrayList<category> Category_report(String category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        DatabaseConfiguration.connectDB();

        PreparedStatement ps = DatabaseConfiguration.connection.prepareStatement("select Category,sum(Quantity),sum(No_of_sold_items)\r\n"
                + "from `e_commerce__`.`Item`\r\n" + "where Category = '" + category + "'" + "group by Category\r\n" + "");

        ResultSet rs = ps.executeQuery();

        ArrayList<category> a = new ArrayList<category>();

        while (rs.next()) {
            category c = new category();
            c.setName(rs.getString(1));
            c.setQuantity(rs.getInt(2));
            c.setNo_of_sold_items(rs.getInt(3));
            a.add(c);
        }

        return a;
    }

}



