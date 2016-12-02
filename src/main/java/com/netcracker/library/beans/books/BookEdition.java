package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.enums.Bookbinding;

import java.util.*;

/**
 * Created by raumo0 on 18.10.16.
 */
public class BookEdition extends Entity implements Comparable<BookEdition> {
    private static final long serialVersionUID = 1L;
    private String title;
    private int pageCount;
    private GregorianCalendar releaseYear;
    private String description;
    private int isbn;
    private int weight;
    private Bookbinding bookbinding;
    private String image;
    private Collection<Author> authors;
    private Collection<Book> books;

    public BookEdition() {}

    public BookEdition(BookEdition bookEdition) {
        super(bookEdition);
        this.title = bookEdition.getTitle();
        this.pageCount = bookEdition.getPageCount();
        this.releaseYear = bookEdition.getReleaseYear();
        this.description = bookEdition.getDescription();
        this.isbn = bookEdition.getIsbn();
        this.weight = bookEdition.getWeight();
        this.bookbinding = bookEdition.getBookbinding();
        this.image = bookEdition.getImage();
        this.authors = bookEdition.getAuthors();
        this.books = bookEdition.getBooks();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public GregorianCalendar getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(GregorianCalendar releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Bookbinding getBookbinding() {
        return bookbinding;
    }

    public void setBookbinding(Bookbinding bookbinding) {
        this.bookbinding = bookbinding;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BookEdition that = (BookEdition) o;

        if (pageCount != that.pageCount) return false;
        if (isbn != that.isbn) return false;
        if (weight != that.weight) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (bookbinding != that.bookbinding) return false;
        return image != null ? image.equals(that.image) : that.image == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + pageCount;
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + isbn;
        result = 31 * result + weight;
        result = 31 * result + (bookbinding != null ? bookbinding.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookEdition{" +
                "title='" + title + '\'' +
                ", pageCount=" + pageCount +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", isbn=" + isbn +
                ", weight=" + weight +
                ", bookbinding=" + bookbinding +
                ", image='" + image + '\'' +
                "} " + super.toString();
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
    public int compareTo(BookEdition o) {
        return this.getId() - o.getId();
    }
}
