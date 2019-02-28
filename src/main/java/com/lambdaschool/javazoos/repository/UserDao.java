package com.lambdaschool.javazoos.repository;

import com.lambdaschool.javazoos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
