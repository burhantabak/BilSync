package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public String login(){
        @RequestBody AuthenticationRequestBody body;
        au
        return tokenService.generateToken("tuna.saygin@ug.bilkent.edu.tr");
    }
}
