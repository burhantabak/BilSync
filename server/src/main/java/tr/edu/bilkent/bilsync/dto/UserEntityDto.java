package tr.edu.bilkent.bilsync.dto;

public class UserEntityDto {

    private String email;
    private String name;
    private String profileImagePath;
    private boolean isBanned;

    // Constructors
    public UserEntityDto() {}

    public UserEntityDto(String email, String name, String profileImagePath, boolean isBanned) {
        this.email = email;
        this.name = name;
        this.profileImagePath = profileImagePath;
        this.isBanned = isBanned;
    }

    // Getters and Setters
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getProfileImagePath() { return profileImagePath; }

    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }

    public boolean getIsBanned() { return isBanned; }

    public void setIsBanned(boolean isBanned) { this.isBanned = isBanned; }
}
