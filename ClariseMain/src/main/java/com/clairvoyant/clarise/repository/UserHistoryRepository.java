package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, String> {

}
