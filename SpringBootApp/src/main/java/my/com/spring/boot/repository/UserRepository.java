package my.com.spring.boot.repository;

import my.com.spring.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, String>, CrudRepository<User,String> {
    User findByUsernameOrEmail(String _usernameOrEmail,String usernameOrEmail);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
