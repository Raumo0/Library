package com.netcracker.library.entities.users;

import com.netcracker.library.entities.Entity;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Role extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private List<User> users;
    private List<RoleHistory> roleHistories;

    public Role() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (description != null ? !description.equals(role.description) : role.description != null) return false;
        if (users != null ? !users.equals(role.users) : role.users != null) return false;
        return roleHistories != null ? roleHistories.equals(role.roleHistories) : role.roleHistories == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        result = 31 * result + (roleHistories != null ? roleHistories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", roleHistories=" + roleHistories +
                "} " + super.toString();
    }
}
