package tr.edu.bilkent.bilsync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
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
    public ResponseEntity register(@RequestBody UserEntity user) {
        if(authService.register(user)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
