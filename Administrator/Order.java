import java.util.ArrayList;
import java.sql.*;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable 
{
	
	private static Connection connection = null;

    int orderId;

    Date orderDate;

    Date DeliveryDate;

    Double paidAmount;
    
    String user_name;


    public Order() {
    	
    }

    public Order(int orderId, Date orderDate, Date deliveryDate, Double paidAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        DeliveryDate = deliveryDate;
        this.paidAmount = paidAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        DeliveryDate = deliveryDate;
    }
    

    public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }
    
    public static ArrayList<Order> Order_report() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
    	 connectDB();
		 
		 PreparedStatement ps = connection.prepareStatement("select *\r\n" + "from `e_commerce__`.`order_`");
		 
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
    
    
    public static void connectDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
		String databaseName = "";
		
		String url = "jdbc:mysql://localhost:3306/"+databaseName;
		
	    String username = "root";
	    
	    String password = "Mr@123456";
	    
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
	    connection =DriverManager.getConnection(url, username, password);
		 
	}
    
    
    
    
    
    
    
    
}
