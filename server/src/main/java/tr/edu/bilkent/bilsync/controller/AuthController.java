package tr.edu.bilkent.bilsync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.controller.controllerEntities.ForgotPasswordRequestBody;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin
    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequestBody body) {
        AuthenticationResponse response = authService.authenticate(body);
        if (response == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/weather")
    public String weather(){
        return "Hava karanlık";
    }

    @CrossOrigin
    @PostMapping("/registerUser")
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        if(authService.register(userEntity)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @CrossOrigin
    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody String claimedEmail){
        boolean result = authService.forgotPasswordUrl(claimedEmail);
        if(result){
            return ResponseEntity.ok("Message successfully sent");
        }
        return ResponseEntity.badRequest().build();
    }
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
