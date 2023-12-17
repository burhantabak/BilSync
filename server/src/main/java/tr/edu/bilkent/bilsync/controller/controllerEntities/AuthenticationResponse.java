package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response structure for authentication.
 * Used to serialize authentication responses to JSON.
 */
public class AuthenticationResponse {
    @JsonProperty("token")
    public String token;
    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    @JsonProperty("isAdmin")
    public boolean isAdmin;
    @JsonProperty("userId")
    public long userId;

    /**
     * Constructor for creating an AuthenticationResponse object.
     *
     * @param token   The authentication token.
     * @param name    The name of the user.
     * @param email   The email of the user.
     * @param isAdmin Indicates whether the user is an admin.
     * @param userId  The user ID.
     */
    public AuthenticationResponse(String token, String name, String email, boolean isAdmin, long userId){
        this.userId = userId;
        this.token = token;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
