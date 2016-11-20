package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.DAOFactory;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 17.11.16.
 */
public class DatabaseAuthorDAOTest {
    private DAOFactory factory;
    private AuthorDAO authorDAO;
    private Author author;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
    }
    @Before
    public void setUp() throws Exception {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        authorDAO = factory.getAuthorDAO();
        author = new Author();
        author.setFirstName("Mike");
        author.setLastName("Mickelson");
        author.setBiography("I am a man!");
        author.setId(authorDAO.insert(author));
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        authorDAO = null;
        author = null;
    }

    @Test
    public void insert() throws Exception {
        Author author2;
        author2 = authorDAO.getById(author.getId());
        Assert.assertEquals(author, author2);
    }

    @Test
    public void update() throws Exception {
        Author author2;
        author2 = new Author(author);
        boolean a = author.equals(author2);
        Assert.assertTrue(a);
        author2.setBiography("This is new biography");
        authorDAO.update(author2);
        Assert.assertFalse(author.equals(author2));
    }

    @Test
    public void deleteById() throws Exception {
        Author author2;
        boolean deleted = authorDAO.deleteById(author.getId());
        Assert.assertTrue(deleted);
        author2 = authorDAO.getById(author.getId());
        Assert.assertTrue(!author.equals(author2));
    }

    @Test
    public void DeleteAll() throws Exception {
        Assert.assertTrue(authorDAO.deleteAll());
        Assert.assertTrue(authorDAO.getAll().size() == 0);
        List<Author> authors = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            Author newAuthor = new Author(author);
            newAuthor.setPersonId(0);
            newAuthor.setId(authorDAO.insert(newAuthor));
            authors.add(newAuthor);
        }
        Assert.assertTrue(authorDAO.getAll().size() == authors.size());
        Assert.assertTrue(authorDAO.deleteAll());
        Assert.assertTrue(authorDAO.getAll().size() == 0);
    }
}
