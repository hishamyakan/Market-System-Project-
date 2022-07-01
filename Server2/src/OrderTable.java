import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderTable {

    public static ArrayList<Order> getOrders(String username){

            String query = "SELECT * FROM ORDER_";

        if(username != null){

                query = query + " WHERE USERNAME = \""+username+"\" ;";

            }

            else{
                query = query + ";";
            }

            try {
                DatabaseConfiguration.connectDB();
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery();

                ArrayList<Order> items = new ArrayList<>();
                while (resultSet.next()) {

                    Order o = new Order();

                   o.setOrderId(resultSet.getInt(1));
                   o.setOrderDate(resultSet.getDate(2));
                    o.setDeliveryDate(resultSet.getDate(3));
                   o.setPaidAmount(resultSet.getDouble(4));
                   o.setUser_name(resultSet.getString(5));

                    items.add(o);

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


    public static ArrayList<Order> Order_report() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        DatabaseConfiguration.connectDB();


        Connection connection = DatabaseConfiguration.connection;

        String query = "SELECT * FROM ORDER_;";

        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ArrayList<Order> a = new ArrayList<Order>();

        while(rs.next()) {
            Order o = new Order();
            o.setOrderId(rs.getInt(1));
            o.setOrderDate(rs.getDate(2));
            o.setDeliveryDate(rs.getDate(3));
            o.setPaidAmount(rs.getDouble(4));
            o.setUser_name(rs.getString(5));
            a.add(o);
        }

        return a;
    }


    public static ArrayList<Order> Order_report(String Month) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        DatabaseConfiguration.connectDB();

        Connection connection = DatabaseConfiguration.connection;

        if(Month.length() == 1){
            Month = "0"+Month;
        }

        String query = "SELECT * FROM ORDER_ WHERE ORDER_DATE LIKE \"%-"+Month+"-%\";";

        PreparedStatement ps = connection.prepareStatement(query);

        //ps.setString(1,Month);

        ResultSet rs = ps.executeQuery();

        ArrayList<Order> a = new ArrayList<Order>();

        while(rs.next()) {
            Order o = new Order();
            o.setOrderId(rs.getInt(1));
            o.setOrderDate(rs.getDate(2));
            o.setDeliveryDate(rs.getDate(3));
            o.setPaidAmount(rs.getDouble(4));
            o.setUser_name(rs.getString(5));
            a.add(o);
        }

        return a;
    }

    public static ArrayList<Item> getItemsFromOrder(String order_id){

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

        Connection connection = DatabaseConfiguration.connection;

        String query = "SELECT ORDER_.ORDER_ID , ITEM.SERIAL_NUMBER , NAME , PRICE , Sold_quantity , DESCRIPTION , CATEGORY" +
                   " FROM ORDER_ , ITEM , CONTAIN " +
                "WHERE ORDER_.ORDER_ID = CONTAIN.ORDER_ID AND CONTAIN.SERIAL_NUMBER = ITEM.SERIAL_NUMBER AND ORDER_.ORDER_ID = "+order_id+";";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet set = ps.executeQuery();

            ArrayList<Item> arr = new ArrayList<>();
            while(set.next()){

                Item newItem = new Item();

                newItem.setSerialNumber(set.getString(2));
                newItem.setName(set.getString(3));

                newItem.setPrice(set.getDouble(4));
                newItem.setQuantity(set.getShort(5));
                newItem.setDescription(set.getString(6));
                newItem.setCategory(set.getString(7));
                arr.add(newItem);

            }

            return arr;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static synchronized void storeOrder(Order order){

        try {
            DatabaseConfiguration.connectDB();
            Connection connection = DatabaseConfiguration.connection;

            /*Step 1 : Store in Order relation*/
            String query = "INSERT INTO ORDER_(Order_Date , Paid_Amount , username) VALUES(? , ? , ?) ;";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setDate(1 , new java.sql.Date(order.getOrderDate().getTime()));
            ps.setDouble(2 , order.paidAmount);
            ps.setString(3 , order.user_name);

            ps.execute();

            /*Step 2 : Get the new orderId*/

            query = "SELECT MAX(ORDER_ID) FROM ORDER_;";
            PreparedStatement ps2 = connection.prepareStatement(query);

            ResultSet resultSet = ps2.executeQuery();

            resultSet.next();

            String orderId = resultSet.getString(1);

            System.out.println(orderId);
            /*Step 3 : Store items in contain relation*/
            for(Item i : order.items){

                ItemTable.storeInContain(orderId , i);
                ItemTable.modifyQuantities(i);
            }


        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    }