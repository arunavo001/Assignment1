package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    public Optional<Role> findByRoleTypeName(String roleTypeName);
}
