package com.netcracker.library;

/**
 * Created by raumo0 on 14.10.16.
 */
public abstract class Person {
    private String fName;
    private String sName;
    private String email;
    private long id;

    public Person(String fName, String sName, String email, long id) {
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (fName != null ? !fName.equals(person.fName) : person.fName != null) return false;
        if (sName != null ? !sName.equals(person.sName) : person.sName != null) return false;
        return email != null ? email.equals(person.email) : person.email == null;

    }

    @Override
    public int hashCode() {
        int result = fName != null ? fName.hashCode() : 0;
        result = 31 * result + (sName != null ? sName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
