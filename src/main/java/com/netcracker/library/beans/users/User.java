package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Person;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.enums.UserRole;

import java.util.LinkedList;

/**
 * Created by raumo0 on 15.11.16.
 */
public class User extends Person {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String salt;
    private String mobile_phone;
    private String email;
    private LinkedList<Rental> rentals;
    private UserRole role;

    public User() {
    }

    public User(User user){
        super(user);
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.salt = user.getSalt();
        this.mobile_phone = user.getMobile_phone();
        this.email = user.getEmail();
        this.rentals = user.getRentals();
        this.role = user.getRole();
    }

    public User(Person person) {
        super(person);
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(LinkedList<Rental> rentals) {
        this.rentals = rentals;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (mobile_phone != null ? mobile_phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }
}
