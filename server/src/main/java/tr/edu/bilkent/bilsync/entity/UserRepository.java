package tr.edu.bilkent.bilsync.entity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
//After the implementation of the database this class will get the user objects from database
@Repository
public class UserRepository {
    private List<User> userList;
    public UserRepository(){
        userList = new ArrayList<>();
        userList.add(new User("Tuna","Saygın","tuna.saygin@ug.bilkent.edu.tr","tuna"));
        userList.add(new User("Kenan","Zeynalov","kanan.zeyanlov@ug.bilkent.edu.tr","kenan"));
        userList.add(new User("Burhan","Tabak","burhan.tabak@ug.bilkent.edu.tr","burhan"));
    }
    public User findByEmail(String email){
        for(User user: userList){
            if(user.email.equals(email)) {
                return user;
            }
        }
        return null;
    }
}
