package tr.edu.bilkent.bilsync.entity;

public class User {
    String name;
    String surname;
    String email;
    String password;
    public User(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    public boolean checkCred(String claimedEmail, String claimedPassword){
        if(email.equals(claimedEmail) && password.equals(claimedPassword)){
            return true;
        }
        return false;
    }
}
