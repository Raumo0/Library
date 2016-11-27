package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.Bookbinding;
import org.junit.*;

import java.util.*;

/**
 * Created by raumo0 on 20.11.16.
 */
public class MysqlBookEditionDAOTest {
    private static DAOFactory factory;
    private static BookEditionDAO bookEditionDAO;
    private BookEdition bookEdition;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        bookEditionDAO = factory.getBookEditionDAO();
    }

    @Before
    public void setUp() throws Exception {
        bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn((int)(counter + date.getTime()));
        counter += 1;
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(bookEditionDAO.insert(bookEdition));
    }

    @After
    public void tearDown() throws Exception {
        bookEdition = null;
    }

    @Test
    public void insert() throws Exception {
        BookEdition bookEdition2;
        bookEdition2 = bookEditionDAO.getById(bookEdition.getId());
        Assert.assertEquals(bookEdition, bookEdition2);
    }

    @Test
    public void update() throws Exception {
        BookEdition bookEdition2;
        bookEdition2 = bookEditionDAO.getById(bookEdition.getId());
        Assert.assertTrue(bookEdition.equals(bookEdition2));
        Assert.assertNotSame(bookEdition, bookEdition2);
        bookEdition2.setTitle("newTitle");
        bookEdition.setTitle("newTitle");
        bookEdition2.setPageCount(192);
        bookEdition.setPageCount(192);
        bookEdition2.setDescription("newDescription");
        bookEdition.setDescription("newDescription");
        bookEdition2.setWeight(935);
        bookEdition.setWeight(935);
        bookEdition2.setBookbinding(Bookbinding.SOFT);
        bookEdition.setBookbinding(Bookbinding.SOFT);
        bookEditionDAO.update(bookEdition2);
        Assert.assertEquals(bookEdition, bookEditionDAO.getById(bookEdition2.getId()));
    }

    @Test
    public void deleteById() throws Exception {
        BookEdition bookEdition2;
        boolean deleted = bookEditionDAO.deleteById(bookEdition.getId());
        Assert.assertTrue(deleted);
        bookEdition2 = bookEditionDAO.getById(bookEdition.getId());
        Assert.assertTrue(!bookEdition.equals(bookEdition2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getAll().size() == 0);
        List<BookEdition> bookEditions = new LinkedList<>();
        BookEdition newBookEdition;
        for (int i = 0; i < 10; i++){
            newBookEdition = new BookEdition(bookEdition);
            newBookEdition.setIsbn((int)(counter + date.getTime()));
            counter += 1;
            newBookEdition.setId(bookEditionDAO.insert(newBookEdition));
            bookEditions.add(newBookEdition);
        }
        Assert.assertTrue(bookEditionDAO.getAll().size() == bookEditions.size());
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getAll().size() == 0);
    }

    @Test
    public void getBookEditionsByAuthorId() throws Exception {
        Author author = new Author();
        author.setFirstName("Mike");
        author.setLastName("Mickelson");
        author.setBiography("I am a man!");
        author.setId(factory.getAuthorDAO().insert(author));

        bookEdition.setAuthors(new LinkedList<Author>());
        bookEdition.getAuthors().add(author);
        bookEditionDAO.update(bookEdition);
        BookEdition bookEdition2 = bookEditionDAO.getBookEditionsByAuthorId(
                bookEdition.getAuthors().getFirst().getId()).getFirst();
        bookEdition2.setAuthors(new LinkedList<Author>());
        bookEdition2.getAuthors().add(factory.getAuthorDAO().getById(author.getId()));
        Assert.assertEquals(bookEdition, bookEdition2);
    }

    @Test
    public void getBookEditionByBookId() throws Exception {
        Book book = new Book();
        book.setBookEdition(bookEdition);
        book.setBookPosition(BookPosition.STORE);
        book.setBookState(BookState.EXCELLENT);
        book.setId(factory.getBookDAO().insert(book));

        Assert.assertEquals(bookEdition, bookEditionDAO.getBookEditionByBookId(book.getId()));
    }

    @Test
    public void getBookEditionsByGap() throws Exception {
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getAll().size() == 0);
        List<BookEdition> bookEditions = new LinkedList<>();
        BookEdition newBookEdition;
        for (int i = 0; i < 10; i++){
            newBookEdition = new BookEdition(bookEdition);
            newBookEdition.setIsbn((int)(counter + date.getTime()));
            counter += 1;
            newBookEdition.setId(bookEditionDAO.insert(newBookEdition));
            bookEditions.add(newBookEdition);
        }
        Assert.assertEquals(bookEditionDAO.getBookEditionsByGap(0, 3), bookEditions.subList(0, 0 + 3));
        Assert.assertEquals(bookEditionDAO.getBookEditionsByGap(3, 5), bookEditions.subList(3, 3 + 5));
        Assert.assertEquals(bookEditionDAO.getBookEditionsByGap(2, 8), bookEditions.subList(2, 2 + 8));
        Assert.assertEquals(bookEditionDAO.getBookEditionsByGap(6, 7), bookEditions.subList(6, bookEditions.size()));
    }

    @Test
    public void numberOfRecords() throws Exception {
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getNumberOfRecords() == 0);
        List<BookEdition> bookEditions = new LinkedList<>();
        BookEdition newBookEdition;
        for (int i = 0; i < 10; i++){
            newBookEdition = new BookEdition(bookEdition);
            newBookEdition.setIsbn((int)(counter + date.getTime()));
            counter += 1;
            newBookEdition.setId(bookEditionDAO.insert(newBookEdition));
            bookEditions.add(newBookEdition);
        }
        Assert.assertTrue(bookEditionDAO.getNumberOfRecords() == 10);
    }
}