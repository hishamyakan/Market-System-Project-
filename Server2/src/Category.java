import java.util.ArrayList;
import java.sql.*;
import java.io.Serializable;
import java.util.Date;



public class Category implements Serializable {
	
	
	private static Connection connection = null;
	
	private String name;
	
	private int Quantity=0;
	
	private int No_of_sold_items=0;
	
	
	public Category()
	{
		
	}
	
	public Category(String name, int quantity, int no_of_sold_items)
	{
		super();
		this.name = name;
		Quantity = quantity;
		No_of_sold_items = no_of_sold_items;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return Quantity;
	}
	
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	public int getNo_of_sold_items() {
		return No_of_sold_items;
	}
	
	public void setNo_of_sold_items(int no_of_sold_items) {
		No_of_sold_items = no_of_sold_items;
	}
	
	
	
	
	public static ArrayList<Category> Category_report() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		 connectDB();
		 
		 PreparedStatement ps = connection.prepareStatement("select Category,sum(Quantity),sum(No_of_sold_items)\r\n"
		 		+ "from `e_commerce__`.`Item`\r\n" + "group by Category\r\n"+ "");
		 
	        ResultSet rs = ps.executeQuery();
	        
	        ArrayList<Category> a = new ArrayList<Category>();
	        
	        while(rs.next()) {
	        	Category c = new Category();
	        	c.setName(rs.getString(1));
	        	c.setQuantity(rs.getInt(2));
	        	c.setNo_of_sold_items(rs.getInt(3));
	        	a.add(c);
	        }
	        
	        return a;
		}
	public static ArrayList<Category> Category_report(String category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		 connectDB();
		 
		 PreparedStatement ps = connection.prepareStatement("select Category,sum(Quantity),sum(No_of_sold_items)\r\n"
		 		+ "from `e_commerce__`.`Item`\r\n" +"where Category = '"+category+"'"+ "group by Category\r\n"+ "");
		 
	        ResultSet rs = ps.executeQuery();
	        
	        ArrayList<Category> a = new ArrayList<Category>();
	        
	        while(rs.next()) {
	        	Category c = new Category();
	        	c.setName(rs.getString(1));
	        	c.setQuantity(rs.getInt(2));
	        	c.setNo_of_sold_items(rs.getInt(3));
	        	a.add(c);
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
	

