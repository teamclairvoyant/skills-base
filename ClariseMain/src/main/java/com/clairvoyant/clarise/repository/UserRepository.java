package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
