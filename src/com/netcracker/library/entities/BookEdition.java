package com.netcracker.library.entities;

import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.BookLanguage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 18.10.16.
 */
public class BookEdition implements Serializable {
    private long id;
    private LinkedList<Book> books;
    private GregorianCalendar releaseDate;
    private String title;
    private ArrayList<Author> authors;
    private String description;
    private String isbn;
    private List<BookCategory> categories;
    private BookLanguage language;
    private int pageCount;

    public BookEdition(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public BookEdition(long id, String title, ArrayList<Author> authors, String isbn, List<BookCategory> categories) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.categories = categories;
    }

    public BookEdition(long id, LinkedList<Book> books, GregorianCalendar releaseDate, String title,
                       ArrayList<Author> authors, String description, String isbn, List<BookCategory> categories,
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

    public ArrayList<Author> getAuthor() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
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
        return "BookEdition{" +
                "id=" + id +
                ", books=" + books +
                ", releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categories=" + categories +
                ", language=" + language +
                ", pageCount=" + pageCount +
                '}';
    }
}
