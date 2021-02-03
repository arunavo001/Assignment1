package com.upgrad.mba.services;

import com.upgrad.mba.entities.Role;
import com.upgrad.mba.exceptions.RoleTypeDetailsNotFoundException;

import java.util.List;

public interface RoleTypeService {
    public Role acceptRoleTypeDetails(Role role);
    public Role getRoleTypeDetails(int id) throws RoleTypeDetailsNotFoundException;
    public Role getRoleTypeDetailsFromRoleTypeName(String roleType) throws RoleTypeDetailsNotFoundException;
    public boolean deleteRoleType(int id) throws RoleTypeDetailsNotFoundException;
    public List<Role> getAllRoleTypeDetails();
}
