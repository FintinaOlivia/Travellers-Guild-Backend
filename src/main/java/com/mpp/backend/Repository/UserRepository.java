package com.mpp.backend.Repository;

import com.mpp.backend.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // @Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
