package com.netcracker.library.entities.users;

import com.netcracker.library.entities.Entity;

import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 15.11.16.
 */
public class RoleHistory extends Entity {
    private static final long serialVersionUID = 1L;
    private GregorianCalendar activationDate;
    private GregorianCalendar deactivationDate;
    private User user;
    private Role role;

    public RoleHistory() {}

    public GregorianCalendar getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(GregorianCalendar activationDate) {
        this.activationDate = activationDate;
    }

    public GregorianCalendar getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(GregorianCalendar deactivationDate) {
        this.deactivationDate = deactivationDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoleHistory that = (RoleHistory) o;

        if (activationDate != null ? !activationDate.equals(that.activationDate) : that.activationDate != null)
            return false;
        if (deactivationDate != null ? !deactivationDate.equals(that.deactivationDate) : that.deactivationDate != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return role != null ? role.equals(that.role) : that.role == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (activationDate != null ? activationDate.hashCode() : 0);
        result = 31 * result + (deactivationDate != null ? deactivationDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleHistory{" +
                "activationDate=" + activationDate +
                ", deactivationDate=" + deactivationDate +
                ", user=" + user +
                ", role=" + role +
                "} " + super.toString();
    }
}
