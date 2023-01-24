package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.UserCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCategoryRepository extends JpaRepository<UserCategoryMapping,String> {

    Optional<List<UserCategoryMapping>> findByUserId(String userId);

    @Query(value = "SELECT category_id FROM USER_CATEGORY_MAPPING WHERE user_id = :userId",  nativeQuery = true)
    List<String> getCategoryIdByUserId(@Param("userId") String userId);
}
