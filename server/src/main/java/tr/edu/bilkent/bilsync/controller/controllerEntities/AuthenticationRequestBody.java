package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequestBody {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
