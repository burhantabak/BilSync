package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.UserRepository;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequestBody request){
        UserEntity userEntity = userRepository.findByEmail(request.email);
        if(userEntity != null && userEntity.getPassword().equals(request.password)){
            return new AuthenticationResponse(tokenService.generateToken(userEntity.getEmail()),
                    userEntity.getName(), userEntity.getEmail());
        }
        return null;
    }

    public boolean register(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
