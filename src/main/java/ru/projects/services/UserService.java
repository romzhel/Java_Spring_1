package ru.projects.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.projects.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);
}
