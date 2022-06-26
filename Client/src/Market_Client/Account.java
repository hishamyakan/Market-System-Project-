package Market_Client;

public class Account {
    private String UserName ;
    private String Password;

    private String Name ;

    private String Address ;

    private String PhoneNumber ;

    private String CreditCardNumber;

    private double CashBalance;
    Account()
    {

    }

    Account(String UserName , String Password)
    {
        this.UserName = UserName;
        this.Password = Password;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public void setPassword(String password) {
        Password = password;
    }
    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public double getCashBalance() {
        return CashBalance;
    }

    public void setCashBalance(double cashBalance) {
        CashBalance = cashBalance;
    }

    @Override
    public String toString() {
        return UserName+"#"+
                Password+"#"+
                Name+"#"+
                Address+"#"+
                PhoneNumber+"#"+
                CreditCardNumber+"#"+
                CashBalance;
    }
}