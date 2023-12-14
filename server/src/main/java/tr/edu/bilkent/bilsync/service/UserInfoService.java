package tr.edu.bilkent.bilsync.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.exception.EmailAlreadyExistsException;
import tr.edu.bilkent.bilsync.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserRepository repository;

    public UserInfoService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

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


}

