package tr.edu.bilkent.bilsync.controller.controllerEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordRequestBody {
    @JsonProperty("token")
    public String token;
    @JsonProperty("newPassword")
    public String newPassword;
}
