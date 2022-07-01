import java.sql.*;
import java.io.Serializable;


public class category implements Serializable {


	private static Connection connection = null;

	private String name;

	private int Quantity = 0;

	private int No_of_sold_items = 0;


	public category() {

	}

	public category(String name, int quantity, int no_of_sold_items) {
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


}




