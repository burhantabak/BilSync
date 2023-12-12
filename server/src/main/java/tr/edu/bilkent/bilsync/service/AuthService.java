package tr.edu.bilkent.bilsync.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.entity.Post;
import tr.edu.bilkent.bilsync.entity.User;
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
        User user = userRepository.findByEmail(request.email);
        if(user != null && user.getPassword().equals(request.password)){
            return new AuthenticationResponse(tokenService.generateToken(user.getEmail()),
                    user.getName(), user.getEmail());
        }
        return null;
    }

    public boolean register(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addPost(User user, Post post){
        if(user == null){
            return false;
        }
        user.addPost(post);
        userRepository.save(user);
        System.out.println(user.getPostList());
        return true;
    }
}
