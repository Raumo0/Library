package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Person;
import com.netcracker.library.beans.business.Rental;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class User extends Person {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String salt;
    private String mobile_phone;
    private Address address;
    private List<Rental> rentals;
    private List<Role> roles;
    private List<RoleHistory> roleHistories;

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        if (mobile_phone != null ? !mobile_phone.equals(user.mobile_phone) : user.mobile_phone != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (rentals != null ? !rentals.equals(user.rentals) : user.rentals != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        return roleHistories != null ? roleHistories.equals(user.roleHistories) : user.roleHistories == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (mobile_phone != null ? mobile_phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (rentals != null ? rentals.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (roleHistories != null ? roleHistories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", address=" + address +
                ", rentals=" + rentals +
                ", roles=" + roles +
                ", roleHistories=" + roleHistories +
                "} " + super.toString();
    }
}
