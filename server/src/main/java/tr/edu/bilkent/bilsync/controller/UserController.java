package tr.edu.bilkent.bilsync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
                        userEntity.getId(),
                        userEntity.getEmail(),
                        userEntity.getName(),
                        userEntity.getProfileImageName(),
                        userEntity.getBio(),
                        userEntity.isBanned()
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
    @GetMapping("/user/editProfile")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public ResponseEntity<?> editUserProfile(@AuthenticationPrincipal UserEntity currentUser,
                                             @RequestParam(required = false) String bio,
                                             @RequestParam(required = false) String profileImageName) {
        try {
            if (bio != null) {
                currentUser.setBio(bio);
            }

            if (profileImageName != null) {
                currentUser.setProfileImageName(profileImageName);
            }
            repo.save(currentUser);

            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user profile");
        }
    }
    //todo create endpoint for profile viewing

    @CrossOrigin
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
