package com.netcracker.library.entities;

import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.BookLanguage;

import java.io.Serializable;
import java.util.*;

/**
 * Created by raumo0 on 18.10.16.
 */
public class BookEdition implements Serializable, Comparable<BookEdition> {
    private long id;
    private LinkedList<Book> books;
    private GregorianCalendar releaseDate;
    private String title;
    private TreeSet<Author> authors;
    private String description;
    private String isbn;
    private List<BookCategory> categories;
    private BookLanguage language;
    private int pageCount;

    public BookEdition(long id, String title) {
        this.id = id;
        this.title = title;
        this.authors = new TreeSet<>();
    }

    public BookEdition(long id, String title, TreeSet<Author> authors, String isbn, List<BookCategory> categories) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.categories = categories;
    }

    public BookEdition(long id, LinkedList<Book> books, GregorianCalendar releaseDate, String title,
                       TreeSet<Author> authors, String description, String isbn, List<BookCategory> categories,
                       BookLanguage language, int pageCount) {
        this.id = id;
        this.books = books;
        this.releaseDate = releaseDate;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.isbn = isbn;
        this.categories = categories;
        this.language = language;
        this.pageCount = pageCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public void setBooks(LinkedList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public Book searchBook(long id){
        //TODO serch
        return null;
    }

    public GregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(GregorianCalendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TreeSet<Author> getAuthor() {
        return authors;
    }

    public void setAuthors(TreeSet<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookCategory> getCategories() {
        return categories;
    }

    public void addCategory(BookCategory bookCategory) {
        this.categories.add(bookCategory);
    }

    public void removeCategory(BookCategory bookCategory){
        this.categories.remove(bookCategory);
    }

    public BookLanguage getLanguage() {
        return language;
    }

    public void setLanguage(BookLanguage language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    //TODO change exception
    public void setPageCount(int pageCount) throws Exception {
        if (pageCount < 0)
            throw new Exception();
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEdition that = (BookEdition) o;

        if (id != that.id) return false;
        if (pageCount != that.pageCount) return false;
        if (books != null ? !books.equals(that.books) : that.books != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (!title.equals(that.title)) return false;
        if (authors != null ? !authors.equals(that.authors) : that.authors != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (categories != null ? !categories.equals(that.categories) : that.categories != null) return false;
        return language == that.language;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (books != null ? books.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + pageCount;
        return result;
    }

    @Override
    public String toString() {
        return "BookEdition{id=" + id + '}';
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
        return (int) (this.getId() - o.getId());
    }
}
