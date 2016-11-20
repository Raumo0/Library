package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.beans.users.Address;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Publisher extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String image;
    private Address address;
    private String email;
    private List<BookEdition> bookEditions;

    public Publisher() {}

    public Publisher(Publisher publisher) {
        super(publisher);
        this.name = publisher.getName();
        this.description = publisher.getDescription();
        this.image = publisher.getImage();
        this.address = publisher.getAddress();
        this.email = publisher.getEmail();
        this.bookEditions = publisher.getBookEditions();
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(List<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Publisher publisher = (Publisher) o;

        if (name != null ? !name.equals(publisher.name) : publisher.name != null) return false;
        if (description != null ? !description.equals(publisher.description) : publisher.description != null)
            return false;
        if (image != null ? !image.equals(publisher.image) : publisher.image != null) return false;
        if (address != null ? !address.equals(publisher.address) : publisher.address != null) return false;
        if (email != null ? !email.equals(publisher.email) : publisher.email != null) return false;
        return bookEditions != null ? bookEditions.equals(publisher.bookEditions) : publisher.bookEditions == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (bookEditions != null ? bookEditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", bookEditions=" + bookEditions +
                "} " + super.toString();
    }
}
