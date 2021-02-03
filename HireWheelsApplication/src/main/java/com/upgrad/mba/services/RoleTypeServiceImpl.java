package com.upgrad.mba.services;

import com.upgrad.mba.dao.RoleDao;
import com.upgrad.mba.entities.Role;
import com.upgrad.mba.exceptions.RoleTypeDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleTypeServiceImpl implements RoleTypeService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role acceptRoleTypeDetails(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role getRoleTypeDetails(int id) throws RoleTypeDetailsNotFoundException {
        return roleDao.findById(id)
                .orElseThrow(
                        () -> new RoleTypeDetailsNotFoundException("RoleType not found with id: " + id)
                );
    }

    @Override
    public Role getRoleTypeDetailsFromRoleTypeName(String roleType) throws RoleTypeDetailsNotFoundException {
        return roleDao.findByRoleTypeName(roleType)
                .orElseThrow(
                        () -> new RoleTypeDetailsNotFoundException("UserType not found with userTypeName: " + roleType)
                );
    }

    @Override
    public boolean deleteRoleType(int id) throws RoleTypeDetailsNotFoundException {
        Role roleType = getRoleTypeDetails(id);
        roleDao.delete(roleType);
        return true;
    }

    @Override
    public List<Role> getAllRoleTypeDetails() {
        return roleDao.findAll();
    }
}
