package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByIsActive(Boolean b);

    Optional<User> findByIdAndIsActive(String id, boolean b);

    User findByEmailAddress(String userName);
}
