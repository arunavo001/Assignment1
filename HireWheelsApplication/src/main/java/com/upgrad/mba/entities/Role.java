package com.upgrad.mba.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Role {

    @Id
    @GeneratedValue
    private int roleTypeId;

    @Column(length = 20, unique = true)
    private String roleTypeName = "User";

    public Role() {
    }

    public Role(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    @Override
    public String toString() {
        return "RoleType{" +
                "roleTypeId=" + roleTypeId +
                ", roleTypeName='" + roleTypeName + '\'' +
                '}';
    }
}
