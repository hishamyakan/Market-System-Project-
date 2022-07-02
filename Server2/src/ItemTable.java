import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ItemTable extends Table {

    public static void modifyQuantities(Item i) {

        String query = "UPDATE ITEM " +
                "SET QUANTITY = QUANTITY - "+i.getQuantity()+
                " , No_of_sold_items = No_of_sold_items + "+i.getQuantity()+
                " WHERE Serial_number = "+i.getSerialNumber()+";";

        try {
            DatabaseConfiguration.connectDB();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DatabaseConfiguration.connection ;
        try {

            PreparedStatement ps= connection.prepareStatement(query);

            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insert(Item item) {

        String query = "INSERT INTO Item (name,Serial_number,description,category,price,quantity) Values (?,?,?,?,? ,?)";
        try (
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query)
        ) {

            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setString(1, item.getSerialNumber());
            ps.setShort(4, item.getQuantity());
            ps.setString(5, item.getDescription());
            //  ps.setBlob(6,  null);
            ps.setString(7, item.getNumberOfSoldItems());
            ps.setString(8, item.getCategory());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item findById(String SerialNumber) {

        String query = "SELECT * FROM ITEM WHERE SERIAL_NUMBER = " + SerialNumber + ";";

        ArrayList<Item> arr = findHelper(query);

        if (arr.isEmpty())
            return null;
        return arr.get(0);
    }

    public ArrayList<Item> findAll() {
        String query = "SELECT * From Item ORDER BY No_of_sold_items DESC LIMIT 10;";
        return  findHelper(query);


    }

    public ArrayList<Item> findBy(String category) {
        String query = "SELECT * From Item WHERE category = '" + category + "';";
        return findHelper(query);
    }

    public ArrayList<Item> findBy(String category, String itemName) {
        String query = "SELECT * From Item WHERE category = '" + category + "' AND name LIKE '%" + itemName + "%';";
        return findHelper(query);
    }


    public ArrayList<Item> findByUsername(String username) {

        String query = "SELECT * FROM Item, Order_, contain where ITEM.SERIAL_NUMBER = CONTAIN.SERIAL_NUMBER AND CONTAIN.ORDER_ID = ORDER_.ORDER_ID AND USERNAME = \"" + username + "\"";

        return findHelper(query);
    }

    //    public void pruchaseItem(Item i){
//
//
//        int quantity = i.getQuantity();
//
//        String query = "UPDATE ITEM SET QUANTITY = QUANTITY - "+quantity+" , "
//
//    }
    private ArrayList<Item> findHelper(String query) {
        try {

            DatabaseConfiguration.connectDB();
            Connection connect = DatabaseConfiguration.connection;
            PreparedStatement ps = connect.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            ArrayList<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String serialNumber = resultSet.getString("serial_Number");
                Short quantity = resultSet.getShort("quantity");
                String description = resultSet.getString("description");
//                Image image = (Image) resultSet.getBlob("image");
                String image = resultSet.getString("image");
                String numberOfSoldItems = resultSet.getString("No_of_sold_items");
                String category = resultSet.getString("category");

                Item item = new Item(name, price, serialNumber, quantity, description, new ArrayList<>() ,image ,numberOfSoldItems, category);
                items.add(item);
            }
            return items;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public static void storeInContain(String orderSerialNo, Item item) {

        String query = "INSERT INTO CONTAIN(sold_quantity , Order_Id , serial_number) values(?,?,?);";

        try {
            DatabaseConfiguration.connectDB();
            Connection connect = DatabaseConfiguration.connection;
            PreparedStatement ps = connect.prepareStatement(query);

            ps.setString(2, orderSerialNo);
            ps.setString(3, item.getSerialNumber());
            ps.setShort(1, item.getQuantity());

            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}