package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Role;
import com.sumerge.vezeeta.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleEnum name);
}
