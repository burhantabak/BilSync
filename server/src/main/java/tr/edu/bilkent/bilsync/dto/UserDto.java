package tr.edu.bilkent.bilsync.dto;

import tr.edu.bilkent.bilsync.entity.UserType;

/**
 * Data Transfer Object (DTO) representing a user. Used for transferring user information between the server and the client.
 */
public class UserDto {

    private String name;
    private String email;
    private UserType accountType;

    /**
     * Constructor for creating a UserDto with specified values.
     *
     * @param name        The name of the user.
     * @param email       The email of the user.
     * @param accountType The account type of the user.
     */
    public UserDto(String name, String email, UserType accountType) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getAccountType() {
        return accountType;
    }

    public void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }


}
