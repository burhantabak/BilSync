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

/**
 * Controller class for handling user-related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    UserRepository userRepository;

    /**
     * Constructor for UserController, injecting the required UserRepository.
     *
     * @param userRepository The repository for accessing user data.
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return Iterable containing all users.
     */
    @CrossOrigin
    @GetMapping("/users")
    public Iterable<UserEntity> listUsers(){
        Iterable<UserEntity> users = userRepository.findAll();
        return users;
    }

    /**
     * Retrieves a list of all users with secure information.
     *
     * @return List of UserEntityDto containing secure user information.
     */
    @CrossOrigin
    @GetMapping("/usersSecure")
    public List<UserEntityDto> listUsersSecure() {
        Iterable<UserEntity> users = userRepository.findAll();

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

    /**
     * Retrieves the user profile information.
     *
     * @return A welcome message for the user profile.
     */
    @CrossOrigin
    @GetMapping("/userProfile")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    /**
     * Edits the user profile based on the provided parameters.
     *
     * @param currentUser       The currently authenticated user.
     * @param bio               The new bio to be set for the user.
     * @param profileImageName  The new profile image name to be set for the user.
     * @return ResponseEntity indicating the result of the operation with an appropriate message.
     */
    @CrossOrigin
    @GetMapping("/editProfile")
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
            userRepository.save(currentUser);

            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user profile");
        }
    }
    //todo create endpoint for profile viewing

    /**
     * Retrieves the admin profile information.
     *
     * @return A welcome message for the admin profile.
     */
    @CrossOrigin
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
