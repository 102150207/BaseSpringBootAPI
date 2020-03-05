package my.com.spring.boot.services;

import my.com.spring.boot.entities.Role;

public interface RoleService {

    Role getRoleByName(String name);
}
