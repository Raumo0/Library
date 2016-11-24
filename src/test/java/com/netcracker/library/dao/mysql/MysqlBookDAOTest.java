package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.enums.*;
import org.junit.*;

import java.util.*;

/**
 * Created by raumo0 on 21.11.16.
 */
public class MysqlBookDAOTest {
    private static DAOFactory factory;
    private static BookEdition bookEdition;
    private static BookDAO bookDAO;
    private Book book;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        bookDAO = factory.getBookDAO();
    }

    @Before
    public void setUp() throws Exception {
        bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn(new Random().nextInt());
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(factory.getBookEditionDAO().insert(bookEdition));
        book = new Book();
        book.setBookEdition(bookEdition);
        book.setBookPosition(BookPosition.STORE);
        book.setBookState(BookState.EXCELLENT);
        book.setId(bookDAO.insert(book));
    }

    @After
    public void tearDown() throws Exception {
        book = null;
    }

    @Test
    public void insert() throws Exception {
        Book book2;
        book2 = bookDAO.getById(book.getId());
        Assert.assertEquals(book, book2);
    }

    @Test
    public void update() throws Exception {
        Book book2;
        book2 = bookDAO.getById(book.getId());
        Assert.assertTrue(book.equals(book2));
        Assert.assertNotSame(book, book2);
        book2.setBookPosition(BookPosition.READING_ROOM);
        book.setBookPosition(BookPosition.READING_ROOM);
        book2.setBookState(BookState.GOOD);
        book.setBookState(BookState.GOOD);
        bookDAO.update(book2);
        Assert.assertEquals(book, bookDAO.getById(book2.getId()));
    }

    @Test
    public void deleteById() throws Exception {
        Book book2;
        boolean deleted = bookDAO.deleteById(book.getId());
        Assert.assertTrue(deleted);
        book2 = bookDAO.getById(book.getId());
        Assert.assertTrue(!book.equals(book2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(bookDAO.deleteAll());
        Assert.assertTrue(bookDAO.getAll().size() == 0);
        List<Book> books = new LinkedList<>();
        Book book2;
        for (int i = 0; i < 10; i++){
            book2 = new Book(book);
            book2.setId(bookDAO.insert(book2));
            books.add(book2);
        }
        Assert.assertTrue(bookDAO.getAll().size() == books.size());
        Assert.assertTrue(bookDAO.deleteAll());
        Assert.assertTrue(bookDAO.getAll().size() == 0);
    }

    @Test
    public void getBookByRentalId() throws Exception {
        User user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        user.setUsername("1ty" + new Date().getTime());
        user.setPassword("password");
        user.setSalt("salt");
        user.setRole(UserRole.READER);
        user.setId(factory.getUserDAO().insert(user));

        User user2 = new User();
        user2.setFirstName("Mike");
        user2.setLastName("Mickelson");
        user2.setUsername("2rty" + new Date().getTime());
        user2.setPassword("password");
        user2.setSalt("salt");
        user2.setRole(UserRole.READER);
        user2.setId(factory.getUserDAO().insert(user2));

        Rental rental = new Rental();
        rental.setComment("commentary");
        rental.setUser(user);
        rental.setBook(book);
        rental.setStateBefore(book.getBookState());
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(factory.getRentalDAO().insert(rental));

        Assert.assertEquals(book, bookDAO.getBookByRentalId(rental.getId()));
    }

    @Test
    public void getBooksByBookEditionId() throws Exception {
        Book book2 = new Book();
        book2.setBookEdition(bookEdition);
        book2.setBookPosition(BookPosition.STORE);
        book2.setBookState(BookState.EXCELLENT);
        book2.setId(bookDAO.insert(book2));

        BookEdition bookEdition2 = new BookEdition();
        bookEdition2.setTitle("title");
        bookEdition2.setPageCount(543);
        bookEdition2.setDescription("description");
        bookEdition2.setIsbn(new Random().nextInt());
        bookEdition2.setWeight(1520);
        bookEdition2.setBookbinding(Bookbinding.HARD);
        bookEdition2.setId(factory.getBookEditionDAO().insert(bookEdition2));

        Book book3 = new Book();
        book3.setBookEdition(bookEdition2);
        book3.setBookPosition(BookPosition.STORE);
        book3.setBookState(BookState.EXCELLENT);
        book3.setId(bookDAO.insert(book3));

        LinkedList<Book> books = new LinkedList<>();
        books.add(book);
        books.add(book2);
        LinkedList<Book> books2 = new LinkedList<>();
        books2.add(book3);

        Assert.assertEquals(books, bookDAO.getBooksByBookEditionId(bookEdition.getId()));
        Assert.assertEquals(books2, bookDAO.getBooksByBookEditionId(bookEdition2.getId()));
    }
}
