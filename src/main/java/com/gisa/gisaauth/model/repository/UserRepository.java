package com.gisa.gisaauth.model.repository;

import com.gisa.gisaauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginAndPassword(String login, String password);

    User findByLogin(String login);
}
