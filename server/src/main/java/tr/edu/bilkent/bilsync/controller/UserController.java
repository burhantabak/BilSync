package tr.edu.bilkent.bilsync.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.bilkent.bilsync.dto.UserEntityDto;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user")
public class UserController {
    UserRepository repo;

    public UserController(UserRepository userRepository) {
        this.repo = userRepository;
    }
    @CrossOrigin
    @GetMapping("/users")
    public Iterable<UserEntity> listUsers(){
        Iterable<UserEntity> users = repo.findAll();
        return users;
    }

    @CrossOrigin
    @GetMapping("/usersSecure")
    public List<UserEntityDto> listUsersSecure() {
        Iterable<UserEntity> users = repo.findAll();

        List<UserEntityDto> userDtoList = StreamSupport.stream(users.spliterator(), false)
                .map(userEntity -> new UserEntityDto(
                        userEntity.getEmail(),
                        userEntity.getName(),
                        userEntity.getProfileImagePath(),
                        userEntity.getIsBanned()
                ))
                .collect(Collectors.toList());

        return userDtoList;
    }

    @CrossOrigin
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @CrossOrigin
    @GetMapping("/user/userProfile")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @CrossOrigin
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
