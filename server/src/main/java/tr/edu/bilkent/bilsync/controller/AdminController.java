package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.exception.EmailAlreadyExistsException;
import tr.edu.bilkent.bilsync.service.UserInfoService;

@RestController
@RequestMapping("/admin/users")
public class AdminController {

    private final UserInfoService userInfoService;

    @Autowired
    public AdminController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PutMapping("/{userId}/change-email")
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



}
