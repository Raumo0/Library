package com.netcracker.library;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.commands.Command;
import com.netcracker.library.enums.*;
import com.netcracker.library.tools.SystemLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by raumo0 on 28.11.16.
 */
public class BeanFactory {
    private static Map<BeanType, Method> beans;
    private Date date = new Date();
    private int counter;
    private Random random;
    private User user;
    private Author author;
    private BookEdition edition;
    private Book book;
    private Rental rental;

    private BeanFactory() {
        beans = new TreeMap<>();
        counter = 1;
        random = new Random();
        try {
            beans.put(BeanType.USER, getClass().getDeclaredMethod("getFillUser"));
            beans.put(BeanType.AUTHOR, getClass().getDeclaredMethod("getFillAuthor"));
            beans.put(BeanType.BOOK_EDITION, getClass().getDeclaredMethod("getFillBookEdition"));
            beans.put(BeanType.BOOK, getClass().getDeclaredMethod("getFillBook"));
            beans.put(BeanType.RENTAL, getClass().getDeclaredMethod("getFillRental"));
        } catch (NoSuchMethodException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
    }

    public static BeanFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Entity getBean(BeanType beanType){
        user = null;
        author = null;
        edition = null;
        book = null;
        rental = null;
        Entity entity = null;
        Method method;
        method = beans.get(beanType);
        try {
            entity = (Entity) method.invoke(this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        return entity;
    }

    private User getFillUser(){
        generateEntities();
        return this.user;
    }

    private User getEmptyUser() {
        User user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        int username = (int)(counter + date.getTime());
        counter += 1;
        if (username < 0)
            username *= -1;
        user.setUsername("username" + username);
        user.setPassword("password");
        user.setSalt("salt");
        user.setRole(UserRole.READER);
        user.setId(0);
        return user;
    }

    private Author getFillAuthor(){
        generateEntities();
        return this.author;
    }

    private Author getEmptyAuthor(){
        Author author = new Author();
        author.setFirstName("Mike");
        author.setLastName("Mickelson");
        author.setBiography("I am a man!");
        author.setId(0);
        return author;
    }

    private BookEdition getFillBookEdition(){
        generateEntities();
        return this.edition;
    }

    private BookEdition getEmptyBookEdition(){
        BookEdition bookEdition = new BookEdition();
        bookEdition.setTitle("title " + random.nextInt(100000));
        bookEdition.setPageCount(200 + random.nextInt(1000));
        bookEdition.setDescription("description " + random.nextInt(100000));
        int isbn = (int)(counter + date.getTime());
        counter += 1;
        if (isbn < 0)
            isbn *= -1;
        bookEdition.setIsbn(isbn);
        bookEdition.setWeight(100 + random.nextInt(1000));
        Bookbinding bookbinding;
        if (random.nextInt(3) == 0)
            bookbinding = Bookbinding.SOFT;
        else bookbinding = Bookbinding.HARD;
        bookEdition.setBookbinding(bookbinding);
        bookEdition.setId(0);
        return bookEdition;
    }

    private Book getFillBook(){
        generateEntities();
        return this.book;
    }

    private Book getEmptyBook(){
        Book book = new Book();
        book.setBookPosition(BookPosition.STORE);
        BookState bookState;
        if (random.nextInt(3) == 0)
            bookState = BookState.EXCELLENT;
        else bookState = BookState.GOOD;
        book.setBookState(bookState);
        book.setId(0);
        return book;
    }

    private Rental getFillRental(){
        generateEntities();
        return this.rental;
    }

    private Rental getEmptyRental() {
        Rental rental = new Rental();
        rental.setComment("commentary");
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(0);
        return rental;
    }

    private void generateEntities() {
        User user;
        List<Book> books = new LinkedList<>();
        List<BookEdition> editions = new LinkedList<>();
        List<Rental> rentals = new LinkedList<>();
        List<Author> authors = new LinkedList<>();
        int count = random.nextInt(3) + 2;
        int counter;

        user = getEmptyUser();
        user.setRentals(new LinkedList<Rental>());
        for (int i = 0; i < count * count; i++){
            books.add(getEmptyBook());
        }
        for (int i = 0; i < count; i++){
            editions.add(getEmptyBookEdition());
        }
        for (int i = 0; i < count * count * count; i++){
            rentals.add(getEmptyRental());
        }
        for (int i = 0; i < count; i++){
            authors.add(getEmptyAuthor());
        }

        counter = 0;
        for (Book book : books){
            book.setRentals(new LinkedList<Rental>());
            for (int i = 0; i < count; i++){
                book.getRentals().add(rentals.get(i + counter));
                rentals.get(i + counter).setBook(book);

                rentals.get(i + counter).setStateBefore(book.getBookState());
                rentals.get(i + counter).setUser(user);
                user.getRentals().add(rentals.get(i + counter));
            }
            counter += count;
        }
        counter = 0;
        for (BookEdition edition : editions){
            edition.setBooks(new LinkedList<Book>());
            for (int i = 0; i < count; i++){
                edition.getBooks().add(books.get(i + counter));
                books.get(i + counter).setBookEdition(edition);
            }
            counter += count;
        }

        for (int i = 0; i < count; i++){
            editions.get(i).setAuthors(new LinkedList<Author>());
            authors.get(i).setBookEditions(new LinkedList<BookEdition>());
        }
        for (int i = 0; i < count; i++){
            editions.get(i).getAuthors().add(authors.get(i));
            authors.get(i).getBookEditions().add(editions.get(i));
            //Maybe too many relations
//            for (int j = count-1; j >= 0; j--) {
//                editions.get(i).getAuthors().add(authors.get(j));
//                authors.get(j).getBookEditions().add(editions.get(i));
//            }
        }

        this.user = user;
        this.rental = rentals.get(0);
        this.edition = editions.get(0);
        this.book = books.get(0);
        this.author = authors.get(0);
    }

    private static class SingletonHolder{
        private static final BeanFactory INSTANCE = new BeanFactory();
    }
}
