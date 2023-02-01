package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleMapping, String> {
    Optional<List<UserRoleMapping>> findByUserId(String userId);

    @Query(value = "SELECT role_id FROM user_role_mapping WHERE user_id = :userId",  nativeQuery = true)
    List<String> getRoleIdByUserId(@Param("userId") String userId);

//    List<UserRoleMapping>  findByRoleIdAndIsActive(String roleId, boolean b);
}
