package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.List;

/**
 * Created by raumo0 on 18.11.16.
 */
public class UserRole extends Entity{
    private boolean isActive;
    private User user;
    private Role role;
    private List<RoleHistory> roleHistories;

    public UserRole() {}

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RoleHistory> getRoleHistories() {
        return roleHistories;
    }

    public void setRoleHistories(List<RoleHistory> roleHistories) {
        this.roleHistories = roleHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserRole userRole = (UserRole) o;

        if (isActive != userRole.isActive) return false;
        if (user != null ? !user.equals(userRole.user) : userRole.user != null) return false;
        if (role != null ? !role.equals(userRole.role) : userRole.role != null) return false;
        return roleHistories != null ? roleHistories.equals(userRole.roleHistories) : userRole.roleHistories == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (roleHistories != null ? roleHistories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "isActive=" + isActive +
                ", user=" + user +
                ", role=" + role +
                ", roleHistories=" + roleHistories +
                "} " + super.toString();
    }
}
