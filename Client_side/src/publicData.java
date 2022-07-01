import java.util.ArrayList;

public class publicData {
    static String name;
    static String password;
    static Account account;
    static double added_money;
    static String order_id;
    static String path;
    static Cart cart=new Cart(new ArrayList<Item>());
    static String money_of_cart;
    static ArrayList<Item>out_of_stock=new ArrayList<>();
}
