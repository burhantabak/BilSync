package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the request body for authentication.
 * Used to deserialize JSON request bodies for authentication endpoints.
 */
public class AuthenticationRequestBody {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
