package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Author extends Person implements Comparable<Author> {
    private static final long serialVersionUID = 1L;
    private String biography;
    private List<BookEdition> bookEditions;

    public Author(){}

    public Author(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
        bookEditions = new ArrayList<BookEdition>();
    }

    public Author(Person person){
        super(person.getFirstName(), person.getLastName(), person.getId());
        bookEditions = new ArrayList<BookEdition>();
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(List<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
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
        return this.getId() - o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (biography != null ? !biography.equals(author.biography) : author.biography != null) return false;
        return bookEditions != null ? bookEditions.equals(author.bookEditions) : author.bookEditions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        result = 31 * result + (bookEditions != null ? bookEditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "biography='" + biography + '\'' +
                ", bookEditions=" + bookEditions +
                "} " + super.toString();
    }
}
