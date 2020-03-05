package my.com.spring.boot.repository;

import my.com.spring.boot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, CrudRepository<Role,String> {
    Role findByName(String name);
}
