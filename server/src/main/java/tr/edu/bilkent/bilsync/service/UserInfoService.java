package tr.edu.bilkent.bilsync.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.User;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserRepository repository;

    public UserInfoService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username);
        return new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
    }
}
