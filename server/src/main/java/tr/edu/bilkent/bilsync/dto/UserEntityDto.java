package tr.edu.bilkent.bilsync.dto;

/**
 * Data Transfer Object (DTO) representing a user entity. Used for transferring user information between the server and the client.
 */
public class UserEntityDto {

    private long id;
    private String email;
    private String name;
    private String profileImageName;
    private String bio;
    private boolean isBanned;


    /**
     * Default constructor for creating a UserEntityDto with default values.
     */
    public UserEntityDto() {}

    /**
     * Constructor for creating a UserEntityDto with specified values.
     *
     * @param id               The unique identifier of the user.
     * @param email            The email of the user.
     * @param name             The name of the user.
     * @param profileImageName The profile image name of the user.
     * @param bio              The biography of the user.
     * @param isBanned         A flag indicating whether the user is banned.
     */
    public UserEntityDto(long id, String email, String name, String profileImageName, String bio, boolean isBanned) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageName = profileImageName;
        this.bio = bio;
        this.isBanned = isBanned;
    }

    // Getters and Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getProfileImageName() { return profileImageName; }

    public void setProfileImageName(String profileImageName) { this.profileImageName = profileImageName; }

    public String getBio() { return this.bio; }

    public void setBio(String bio) { this.bio = bio; }

    public boolean getIsBanned() { return isBanned; }

    public void setIsBanned(boolean isBanned) { this.isBanned = isBanned; }
}
