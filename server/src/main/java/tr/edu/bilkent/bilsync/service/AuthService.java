package tr.edu.bilkent.bilsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.entity.User;
import tr.edu.bilkent.bilsync.entity.UserRepository;

@Service
public class AuthService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequestBody request){
        User user = userRepository.findByEmail(request.email);
        if(user != null && user.getPassword().equals(request.password)){
            return new AuthenticationResponse(tokenService.generateToken(user.getUsername()),
                    user.getName(), user.getSurname(), user.getUsername());
        }
        return null;
    }
}
