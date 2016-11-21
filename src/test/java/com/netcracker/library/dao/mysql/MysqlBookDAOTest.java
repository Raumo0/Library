package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.Bookbinding;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn(new Random().nextInt());
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(factory.getBookEditionDAO().insert(bookEdition));
    }

    @Before
    public void setUp() throws Exception {
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
}
