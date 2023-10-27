package tr.edu.bilkent.bilsync.controller.controllerEntities;

public class AuthenticationRequestBody {
    private String email;
    private String password;

    public String getPassword(){return password;}
    public String getEmail(){return email;}
}
