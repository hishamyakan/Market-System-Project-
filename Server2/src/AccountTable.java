import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTable extends Table {

    public synchronized void insert(Account account, AccountType type){
        String table = getTableName(type);
        String query = "INSERT INTO " + table + " (username,password,name , Credit_card,cash_Balance) Values (?,?,?,?,?);";
        try (
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query)
        ) {

            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getName());


            ps.setString(4, account.getCreditCardNumber());
            ps.setDouble(5, account.getCashBalance());

            ps.execute();


                if(type == AccountType.CLIENT) {
                    String query2 = "INSERT INTO customer_phone(Phone , username) VALUES (? , ?);";

                    PreparedStatement ps2 = connect.prepareStatement(query2);

                    ps2.setString(1, account.getPhoneNumber());

                    ps2.setString(2, account.getUserName());

                    ps2.execute();

                    String query3 = "INSERT INTO customer_address(address , username) VALUES (? , ?);";

                    PreparedStatement ps3 = connect.prepareStatement(query3);

                    ps3.setString(1, account.getAddress());

                    ps3.setString(2, account.getUserName());

                    ps3.execute();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findBy(String username, AccountType type) {
        String table = getTableName(type);
        String query = "SELECT * FROM " + table + " WHERE username = ?";
        try (
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query)
        ) {

            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

            if(!resultSet.next())
                return null;



            String userName = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            String creditCardNumber = null;
            double cashBalance = -1.0;
            String phoneNumber = null;
            String address = null;

            if(type == AccountType.CLIENT) {
                 creditCardNumber = resultSet.getString("credit_Card");
                 cashBalance = resultSet.getDouble("cash_Balance");


                query = "SELECT Phone from customer_phone where username = ?;";

                PreparedStatement ps2 = connect.prepareStatement(query);

                ps2.setString(1, username);

                resultSet = ps2.executeQuery();

                if (!resultSet.next())
                    return null;

                phoneNumber = resultSet.getString(1);

                query = "SELECT Address from customer_address where username = ?;";
                PreparedStatement ps3 = connect.prepareStatement(query);

                ps3.setString(1, username);

                resultSet = ps3.executeQuery();

                if (!resultSet.next())
                    return null;

                address = resultSet.getString(1);
            }
//                String phoneNumber = "" , address  = "";

            return new Account(userName, password, name, address, phoneNumber, creditCardNumber, cashBalance);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void updateAccountBalance(String username,double cashBalance){
        String query = "UPDATE Customer SET cash_Balance = ? WHERE username = ?;";

        try (
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query)
        ) {

            ps.setDouble(1, cashBalance);
            ps.setString(2, username);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBy(String username, AccountType type) {
        String table = getTableName(type);
        String query = "DELETE FROM " + table + " WHERE username = ?;";
        try (
                Connection connect = DatabaseConfiguration.connection;
                PreparedStatement ps = connect.prepareStatement(query)
        ) {

            ps.setString(1, username);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTableName(AccountType type) {
        String table;
        if (type == AccountType.ADMIN)
            table = "Admin";
        else
            table = "Customer";
        return table;
    }
}
