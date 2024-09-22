package com.ChatApplicationServer.repository;


import com.ChatApplicationServer.entity.User;
import com.ChatApplicationServer.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole role);

    List<User> findAllByUserRole(UserRole role);
    List<User> findAllByNameContaining(String name);

}
