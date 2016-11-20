package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 15.11.16.
 */
public class RoleHistory extends Entity {
    private static final long serialVersionUID = 1L;
    private GregorianCalendar activationDate;
    private GregorianCalendar deactivationDate;
    private UserRole userRole;

    public RoleHistory() {}

    public RoleHistory(RoleHistory roleHistory){
        super(roleHistory);
        this.activationDate = roleHistory.getActivationDate();
        this.deactivationDate = roleHistory.getDeactivationDate();
        this.userRole = roleHistory.getUserRole();
    }

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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
        return userRole != null ? userRole.equals(that.userRole) : that.userRole == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (activationDate != null ? activationDate.hashCode() : 0);
        result = 31 * result + (deactivationDate != null ? deactivationDate.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleHistory{" +
                "activationDate=" + activationDate +
                ", deactivationDate=" + deactivationDate +
                ", userRole=" + userRole +
                "} " + super.toString();
    }
}
