package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.enums.Bookbinding;
import org.junit.*;

import java.util.*;

/**
 * Created by raumo0 on 20.11.16.
 */
public class MysqlBookEditionDAOTest {
    private DAOFactory factory;
    private BookEditionDAO bookEditionDAO;
    private BookEdition bookEdition;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
    }

    @Before
    public void setUp() throws Exception {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        bookEditionDAO = factory.getBookEditionDAO();
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
        factory = null;
        bookEditionDAO = null;
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
        BookEdition author2;
        boolean deleted = bookEditionDAO.deleteById(bookEdition.getId());
        Assert.assertTrue(deleted);
        author2 = bookEditionDAO.getById(bookEdition.getId());
        Assert.assertTrue(!bookEdition.equals(author2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getAll().size() == 0);
        List<BookEdition> bookEditions = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            BookEdition newBookEdition = new BookEdition(bookEdition);
            bookEdition.setIsbn((int)(counter + date.getTime()));
            counter += 1;
            newBookEdition.setId(bookEditionDAO.insert(newBookEdition));
            bookEditions.add(newBookEdition);
        }
        Assert.assertTrue(bookEditionDAO.getAll().size() == bookEditions.size());
        Assert.assertTrue(bookEditionDAO.deleteAll());
        Assert.assertTrue(bookEditionDAO.getAll().size() == 0);
    }

}