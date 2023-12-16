package tr.edu.bilkent.bilsync.dto;

import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.entity.UserType;

public class ProfileDto {
    public ProfileDto(UserEntityDto userbase, UserType userType, String bio) {
        this.userbase = userbase;
        this.userType = userType;
        this.bio = bio;
    }

    public UserEntityDto getUserbase() {
        return userbase;
    }

    public void setUserbase(UserEntityDto userbase) {
        this.userbase = userbase;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private UserEntityDto userbase;

    private UserType userType;
    private String bio;


}
