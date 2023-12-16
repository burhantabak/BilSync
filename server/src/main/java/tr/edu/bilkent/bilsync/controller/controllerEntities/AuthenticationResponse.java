package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public AuthenticationResponse(String token, String name, String email, boolean isAdmin, long userId){
        this.userId = userId;
        this.token = token;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
