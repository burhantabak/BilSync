package tr.edu.bilkent.bilsync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.controller.controllerEntities.ForgotPasswordRequestBody;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.service.AuthService;

/**
 * Controller class for handling authentication-related operations.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Constructor for the AuthController class.
     *
     * @param authService The service responsible for handling authentication-related operations.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param body The AuthenticationRequestBody containing user credentials.
     * @return ResponseEntity containing the authentication response if successful, or UNAUTHORIZED status if not.
     */
    @CrossOrigin
    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequestBody body) {
        AuthenticationResponse response = authService.authenticate(body);
        if (response == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Returns a sample weather response.
     *
     * @return A string indicating the weather condition.
     */
    @CrossOrigin
    @GetMapping("/weather")
    public String weather(){
        return "Hava karanlık";
    }


    //DEPRECATED!!!
    /**
     * DEPRECATED: Registers a new user.
     *
     * @param userEntity The UserEntity to be registered.
     * @return ResponseEntity indicating the result of the operation.
     */
    @CrossOrigin
    @PostMapping("/registerUser")
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        if(authService.register(userEntity)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Initiates the process of resetting a user's password by sending a reset link.
     *
     * @param claimedEmail The email for which the password reset is requested.
     * @return ResponseEntity indicating the result of the operation.
     */
    @CrossOrigin
    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody String claimedEmail){
        boolean result = authService.forgotPasswordUrl(claimedEmail);
        if(result){
            return ResponseEntity.ok("Message successfully sent");
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Resets a user's password based on the provided token and new password.
     *
     * @param forgotPasswordRequestBody The request body containing the token and new password.
     * @return ResponseEntity indicating the result of the password reset operation.
     */
    @CrossOrigin
    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ForgotPasswordRequestBody forgotPasswordRequestBody){
        //authService will validify and save the newPassword if password is okay
        if(forgotPasswordRequestBody.newPassword.isBlank()){
            return ResponseEntity.noContent().build();
        }
        boolean result = authService.changePassword(forgotPasswordRequestBody.token,
                forgotPasswordRequestBody.newPassword);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
