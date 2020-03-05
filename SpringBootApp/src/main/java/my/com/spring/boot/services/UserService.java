package my.com.spring.boot.services;

import my.com.spring.boot.entities.User;
import my.com.spring.boot.repository.UserRepository;
import my.com.spring.boot.services.serviceimpl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService  {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);
}
