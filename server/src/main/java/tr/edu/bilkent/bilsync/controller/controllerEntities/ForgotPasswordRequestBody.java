package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the request body for resetting the password.
 * Used to deserialize JSON request bodies for the forgot password endpoint.
 */
public class ForgotPasswordRequestBody {
    @JsonProperty("token")
    public String token;
    @JsonProperty("newPassword")
    public String newPassword;
}
