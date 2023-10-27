package tr.edu.bilkent.bilsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository respository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return respository.findByEmail(username); // todo after database is set this will be changed(Tuna)
    }
}
