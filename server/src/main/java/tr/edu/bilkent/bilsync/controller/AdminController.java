package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.dto.UserDto;
import tr.edu.bilkent.bilsync.exception.EmailAlreadyExistsException;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.service.UserInfoService;

/**
 * Controller class for handling admin-related operations on users.
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/users")
public class AdminController {

    private final UserInfoService userInfoService;

    /**
     * Constructor for the AdminController class.
     *
     * @param userInfoService The service responsible for handling user-related operations.
     */
    @Autowired
    public AdminController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * Updates the email of a user with the specified user ID.
     *
     * @param userId    The ID of the user whose email needs to be updated.
     * @param newEmail  The new email to be set for the user.
     * @return ResponseEntity indicating the result of the operation with an appropriate message.
     */
    @CrossOrigin
    @PostMapping("/{userId}/change-email")
    public ResponseEntity<String> changeEmail(@PathVariable long userId, @RequestBody String newEmail) {
        try {
            userInfoService.changeEmail(userId, newEmail);
            return ResponseEntity.status(HttpStatus.OK).body("Email changed successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    /**
     * Bans a user with the specified user ID.
     *
     * @param userId The ID of the user to be banned.
     * @return ResponseEntity indicating the result of the operation with an appropriate message.
     */
    @PutMapping("/{userId}/ban")
    public ResponseEntity<String> banUser(@PathVariable long userId) {
        try {
            userInfoService.banUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User banned successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (UserIsBannedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already banned");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    /**
     * Adds a new user based on the provided UserDto.
     *
     * @param userDto The UserDto containing user information to be added.
     * @return ResponseEntity indicating the result of the operation with an appropriate message.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        if (userDto.getAccountType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account type can't be empty");
        } else if (userDto.getEmail() == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email can't be empty");
        } else if (userDto.getName() == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username can't be empty");
        }

        try {
            userInfoService.addUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

    }


}
