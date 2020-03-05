package my.com.spring.boot.security;

import my.com.spring.boot.entities.User;
import my.com.spring.boot.exception.ResourceNotFoundException;
import my.com.spring.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String id){
        User user = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Not Found USER by Id","Id","Id")
        );
        return UserPrincipal.create(user);
    }
}
