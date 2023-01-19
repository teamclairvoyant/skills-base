package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.UserDesignationMapping;
import com.clairvoyant.clarise.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleMapping, String> {
    Optional<List<UserRoleMapping>> findByUserId(String userId);

    @Query(value = "SELECT role_id FROM USER_ROLE_MAPPING WHERE user_id = ?1",  nativeQuery = true)
    List<String> getRoleIdByUserId(String userId);

//    List<UserRoleMapping>  findByRoleIdAndIsActive(String roleId, boolean b);
}
