package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("token")
    public String token;
    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("email")
    public String email;

    public AuthenticationResponse(String token, String name, String surname, String email){
        this.token = token;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
