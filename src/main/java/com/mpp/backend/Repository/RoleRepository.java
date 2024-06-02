package com.mpp.backend.Repository;

import com.mpp.backend.Model.Role;
import com.mpp.backend.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByRoleName(String roleName);
}
