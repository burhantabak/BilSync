package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("token")
    public String token;
    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;

    public AuthenticationResponse(String token, String name, String email){
        this.token = token;
        this.name = name;
        this.email = this.email;
    }
}
