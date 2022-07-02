import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainTest {


    public static void main (String[] args)
    {

        try {
            DatabaseConfiguration.connectDB();

            DatabaseManager manager = new DatabaseManager();


            manager.getAllItems("Book" , "Deep");

            for(Item i :  manager.getAllItems("Book" , "Deep")){

                System.out.println(i.getName());

            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }

}



