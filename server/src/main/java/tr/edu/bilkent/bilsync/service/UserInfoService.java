package tr.edu.bilkent.bilsync.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.UserDto;
import tr.edu.bilkent.bilsync.entity.PasswordGenerator;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.exception.EmailAlreadyExistsException;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.repository.UserRepository;

/**
 * Service class for managing user information and related operations.
 */
@Service
public class UserInfoService implements UserDetailsService {
    private final UserRepository repository;
    private final MailService mailService;

    /**
     * Constructor for UserInfoService, injecting the required repositories and services.
     *
     * @param repository   The repository for managing user entities.
     * @param mailService  The service for sending emails.
     */
    public UserInfoService(UserRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    /**
     * Loads user details by username.
     *
     * @param username The username of the user.
     * @return UserDetails for the specified username.
     * @throws UsernameNotFoundException If no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    /**
     * Loads user details by user ID.
     *
     * @param id The ID of the user.
     * @return UserDetails for the specified user ID.
     * @throws UsernameNotFoundException If no user is found with the given user ID.
     */
    public UserDetails loadUserById(long id) throws UsernameNotFoundException {
        return repository.findById(id);
    }

    /**
     * Changes the email of a user identified by the given user ID.
     *
     * @param userId   The ID of the user whose email needs to be changed.
     * @param newMail  The new email to be set for the user.
     * @throws UsernameNotFoundException      If no user is found with the given user ID.
     * @throws EmailAlreadyExistsException     If the new email is already associated with another user.
     */
    public void changeEmail(long userId, String newMail) {
        // Retrieve the user by userId
        UserEntity user = repository.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        } else {
            if (repository.findByEmail(newMail) != null) {
                throw new EmailAlreadyExistsException("Email already exists for another user");
            }
            user.setEmail(newMail);

            // Save the updated user
            repository.save(user);
        }


    }

    /**
     * Bans a user with the specified user ID.
     *
     * @param userId The ID of the user to be banned.
     * @throws UsernameNotFoundException    If no user is found with the given user ID.
     * @throws UserIsBannedException         If the user is already banned.
     */
    public void banUser(long userId) {
        UserEntity user = repository.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        } else if (user.isBanned()) {
            throw new UserIsBannedException("User is already banned");
        }
        user.setBanned(true); // Set the user as banned
        repository.save(user); // Save the updated user
    }

    /**
     * Unbans a user with the specified user ID.
     *
     * @param userId The ID of the user to be unbanned.
     * @throws UsernameNotFoundException If no user is found with the given user ID.
     * @throws IllegalStateException      If the user is not currently banned.
     */
    public void unbanUser(long userId) {
        UserEntity user = repository.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        } else if (!user.isBanned()) {
            throw new IllegalStateException("User is not currently banned");
        }
        user.setBanned(false); // Set the user as unbanned
        repository.save(user); // Save the updated user
    }



    /**
     * Adds a new user based on the provided UserDto.
     *
     * @param userDto The UserDto containing user information to be added.
     * @throws EmailAlreadyExistsException   If the email in the UserDto already exists for another user.
     */
    public void addUser(UserDto userDto) {
        UserEntity user = new UserEntity(userDto);
        if (repository.findByEmail(userDto.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists for another user");
        }
        int passwordLength = 8;
        String password = PasswordGenerator.generateRandomPassword(passwordLength);
        user.setPassword(password);

        String baseUrl = "http://localhost:5173";
        //String endpoint = "/auth/login";

        String mailTitle = "Welcome to BilSync";
        String mailBody = "Hello There,\n\nWe enjoy seeing you in our community. Here is your initial password:\n"
                + password + "\n\nWe encourage to start Syncing with other Bilkenters! You may want to change your password first. Make sure to use the latest version of Google Chrome before starting your journey here: "
                + baseUrl  + "\n\nBilSync DevTeam ";
        String recipientMail = userDto.getEmail();
        mailService.sendMail(mailTitle, mailBody, recipientMail);
        repository.save(user);

    }

    /**
     * Finds a user by ID.
     *
     * @param userId The ID of the user to find.
     * @return The UserEntity object if found, null otherwise.
     */
    public UserEntity findById(long userId)
    {
        return  repository.findById(userId);
    }
}

