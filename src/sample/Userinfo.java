package sample;

public class Userinfo {

    public String name , username, address, email , phonenumber ,birthdate;

    public Userinfo(String name, String username, String address, String email, String phonenumber, String birthdate) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.birthdate = birthdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void getuserdata(){
        if(username.equals("Nada Amgad")){


            name = "Nada Amgad Sayed";
            username = "Nada Amgad";
            email = "ABCD@gmail.com";
            phonenumber = "0123456789";
            birthdate = "dd/mm/yyyy";
            address = "Cairo";


        }
        if(username.equals("Nada Youssef")){
            name = "Nada Youssef Ahmed";
            username = "Nada Youssef";
            email = "ABCD@gmail.com";
            phonenumber = "0123456789";
            birthdate = "dd/mm/yyyy";
            address = "Cairo";

        }

    }

}
