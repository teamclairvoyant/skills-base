package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, String> {

}
