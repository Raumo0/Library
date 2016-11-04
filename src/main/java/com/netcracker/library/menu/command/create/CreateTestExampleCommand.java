package com.netcracker.library.menu.command.create;

import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.ReaderDAO;
import com.netcracker.library.entities.*;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.service.*;
import com.netcracker.library.service.impl.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

/**
 * Created by raumo0 on 27.10.16.
 */
public class CreateTestExampleCommand implements CreateEntityCommand {
    private AuthorService authorService = new AuthorServiceImpl();
    private BookEditionService bookEditionService = new BookEditionServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();
    private LibrarianService librarianService = new LibrarianServiceImpl();
    private Random rnd = new Random();

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            for (int i = 0; i < 5; i++) {
                createAuthor();
            }
            for (int i = 0; i < 10; i++) {
                createBookEdition();
            }
            for (int i = 0; i < 30; i ++) {
                int number = rnd.nextInt(bookEditionService.getBookEditions().size());
                createBook((BookEdition) bookEditionService.getBookEditions().toArray()[number]);
            }
            for (int i = 0; i < 3; i++) {
                createReader();
            }
            serializeReader();

            createLibrarian();

            for (int i = 0; i < authorService.getAuthors().size(); i ++) {
                Author author = (Author) authorService.getById(i+1);
                BookEdition bookEdition = bookEditionService.getById(i+1);
                author.addBookEdition(bookEdition);
                bookEdition.addAuthor(author);
            }
            for (int i = authorService.getAuthors().size()-1; i < bookEditionService.getBookEditions().size(); i++) {
                for (int j = 0; j < rnd.nextInt(5); j++) {
                    Author author = (Author) authorService.getById(rnd.nextInt(5)+1);
                    BookEdition bookEdition = bookEditionService.getById(i+1);
                    author.addBookEdition(bookEdition);
                    bookEdition.addAuthor(author);
                }
            }

            System.out.println(authorService.getAuthors());
            System.out.println(bookEditionService.getBookEditions());
            System.out.println(bookService.getBooks());
            System.out.println(readerService.getReaders());
            System.out.println(librarianService.getLibrarians());
        } else {
            //TODO
        }
        return true;
    }

    private void serializeReader(){
        DAOFactory binaryFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = null;
        try {
            readerDAO = binaryFactory.getReaderDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        readerDAO.saveAll(readerService.getReaders());
        int a = 0;
        for (Reader reader : readerService.getReaders()) {
            a = readerDAO.insert(reader);
        }
        Reader reader = readerDAO.read(a);
        System.out.println("\n\n" + reader + " " + reader.getFirstName() + " " + reader.getLastName() + "\n\n");
        reader.setFirstName("KLJLKJFELKFJ");
        reader.setLastName("KLJFdfkdf");
        readerDAO.update(reader);
        reader = readerDAO.read(a);
        System.out.println("\n\n" + reader + " " + reader.getFirstName() + " " + reader.getLastName() + "\n\n");
        readerDAO.delete(a);
        reader = readerDAO.read(a);
        System.out.println("\n\n" + reader + "\n\n");
        readerService.setReaders(readerDAO.readAll());
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "1";
    }

    @Override
    public String getDescription() {
        return String.format("Creates test entities for use library.");    }

    private Author createAuthor() {
        Collection authors = authorService.getAuthors();
        Integer count = authors.size() + 1;
        Author author = new Author(String.format("author" + count + "FirstName"),
                String.format("author" + count + "lastName"),
                String.format("author" + count + "@gmail.com"), count);
        authorService.insertAuthor(author);
        return author;
    }

    private BookEdition createBookEdition() {
        Collection<BookEdition> bookEditions = bookEditionService.getBookEditions();
        Integer count = bookEditions.size() + 1;
        BookEdition bookEdition = new BookEdition(count, String.format("title" + count));
        bookEditionService.insertBookEdition(bookEdition);
        return bookEdition;
    }

    private Book createBook(BookEdition bookEdition) {
        Book book = new Book(0, bookEdition, BookState.GOOD, BookPosition.IN_STORE);
        bookService.insertBook(book);
        return book;
    }

    private Reader createReader() {
        Collection readers = readerService.getReaders();
        Integer count = readers.size() + 1;
        Reader reader = new Reader(String.format("reader" + count + "FirstName"),
                String.format("reader" + count + "lastName"),
                String.format("reader" + count + "@gmail.com"), count, String.format("login" + count),
                String.format("password" + count));
        readerService.insertReader(reader);
        return reader;
    }

    private Librarian createLibrarian() {
        Collection librarians = readerService.getReaders();
        Integer count = librarians.size() + 1;
        Librarian librarian = new Librarian(String.format("librarian" + count + "FirstName"),
                String.format("librarian" + count + "lastName"),
                String.format("librarian" + count + "@gmail.com"), count);
        librarianService.insertLibrarian(librarian);
        return librarian;
    }
}
