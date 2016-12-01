package com.netcracker.library;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
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
        User user;
        user = getEmptyUser();
        //todo
        return user;
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
        Author author;
        BookEdition bookEdition;
        author = getEmptyAuthor();
        author.setBookEditions(new LinkedList<BookEdition>());
        Collection<Author> authors = new LinkedList<>();
        authors.add(author);
        int bookCount = random.nextInt(5) + 1;
        for (int i = 0; i < bookCount; i++){
            bookEdition = getEmptyBookEdition();
            bookEdition.setAuthors(authors);
            author.getBookEditions().add(bookEdition);
        }
        return author;
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
        BookEdition bookEdition;
        bookEdition = getEmptyBookEdition();
        bookEdition.setAuthors(new LinkedList<Author>());
        int authorCount = random.nextInt(4) + 1;
        for (int i = 0; i < authorCount; i++){
            Author author = getEmptyAuthor();
            author.setBookEditions(new LinkedList<BookEdition>());
            author.getBookEditions().add(bookEdition);
            bookEdition.getAuthors().add(author);
        }
        bookEdition.setBooks(new LinkedList<Book>());
        if (random.nextInt(5) != 0) {
            int bookCount = random.nextInt(20);
            for (int i = 0; i < bookCount; i++) {
                Book book = getEmptyBook();
                book.setBookEdition(bookEdition);
                bookEdition.getBooks().add(book);
            }
        }
        bookEdition.setId(0);
        return bookEdition;
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
        Book book;
        BookEdition bookEdition;
        book = getEmptyBook();
        bookEdition = getEmptyBookEdition();
        book.setBookEdition(bookEdition);
        return book;
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
        Rental rental;
        User user;
        Book book;
        BookEdition bookEdition;
        rental = getEmptyRental();
        book = getEmptyBook();
        bookEdition = getEmptyBookEdition();
        book.setBookEdition(bookEdition);
        user = getEmptyUser();
        rental.setUser(user);
        rental.setBook(book);
        rental.setStateBefore(book.getBookState());
        return rental;
    }

    private Rental getEmptyRental() {
        Rental rental = new Rental();
        rental.setComment("commentary");
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(0);
        return rental;
    }

    private static class SingletonHolder{
        private static final BeanFactory INSTANCE = new BeanFactory();
    }
}
