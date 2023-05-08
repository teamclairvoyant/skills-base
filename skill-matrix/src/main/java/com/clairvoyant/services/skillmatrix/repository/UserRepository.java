package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select * from skillbasedb.user u where u.name LIKE CONCAT('%',:name,'%') "
            + "or u.email LIKE CONCAT('%',:email,'%')",
            countQuery = "select count(*) from skillbasedb.user u where u.name LIKE CONCAT('%',:name,'%')"
            + "or u.email LIKE CONCAT('%',:email,'%')",
            nativeQuery = true)
    Page<User> search(@Param("name") String name, @Param("email") String email, Pageable pageable);

    List<User> findByIsActive(Boolean b);

    Optional<User> findByIdAndIsActive(String id, boolean b);

    User findByEmailAddress(String userName);
}
