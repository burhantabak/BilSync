package tr.edu.bilkent.bilsync.dto;

public class UserEntityDto {

    private long id;
    private String email;
    private String name;
    private String profileImageName;
    private boolean isBanned;

    // Constructors
    public UserEntityDto() {}

    public UserEntityDto(long id, String email, String name, String profileImageName, boolean isBanned) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImageName = profileImageName;
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

    public boolean getIsBanned() { return isBanned; }

    public void setIsBanned(boolean isBanned) { this.isBanned = isBanned; }
}
