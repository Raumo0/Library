package com.netcracker.library.entities;

import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.Country;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Author extends Person implements Comparable<Author> {
    private TreeSet<BookEdition> bookEditions;
    private String bio;
    private BookCategory bookCategory;

    public Author(String firstName, String lastName, String email, long id) {
        super(firstName, lastName, email, id);
        bookEditions = new TreeSet<>();
    }

    public Author(String firstName, String lastName, String email, long id, GregorianCalendar birthday,
                  Country country, TreeSet<BookEdition> bookEditions, String bio, BookCategory bookCategory) {
        super(firstName, lastName, email, id, birthday, country);
        this.bookEditions = bookEditions;
        this.bio = bio;
        this.bookCategory = bookCategory;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public TreeSet<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(TreeSet<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    public void addBookEdition(BookEdition bookEdition) {
        this.bookEditions.add(bookEdition);
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Author o) {
        return (int) (this.getId() - o.getId());
    }
}
