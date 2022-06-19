package Market_Server;

public class Account {

    String UserName ;
    String Password;

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

}
