package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.enums.Bookbinding;
import org.junit.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 17.11.16.
 */
public class MysqlAuthorDAOTest {
    private static DAOFactory factory;
    private static AuthorDAO authorDAO;
    private Author author;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        authorDAO = factory.getAuthorDAO();
    }
    @Before
    public void setUp() throws Exception {
        author = new Author();
        author.setFirstName("Mike");
        author.setLastName("Mickelson");
        author.setBiography("I am a man!");
        author.setId(authorDAO.insert(author));
    }

    @After
    public void tearDown() throws Exception {
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
        author2 = authorDAO.getById(author.getId());
        Assert.assertTrue(author.equals(author2));
        Assert.assertNotSame(author, author2);
        author2.setBiography("This is new biography");
        author.setBiography("This is new biography");
        authorDAO.update(author2);
        Assert.assertEquals(author, authorDAO.getById(author2.getId()));
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

    @Test
    public void getAuthorByPersonId() throws Exception {
        Assert.assertEquals(author, authorDAO.getAuthorByPersonId(author.getPersonId()));
    }

    @Test
    public void getAuthorsByBookEditionId() throws Exception {
        BookEdition bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn((int)(new Date().getTime()));
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(factory.getBookEditionDAO().insert(bookEdition));

        author.setBookEditions(new LinkedList<BookEdition>());
        author.getBookEditions().add(bookEdition);
        authorDAO.update(author);
        Author author2 = authorDAO.getAuthorsByBookEditionId(author.getBookEditions().iterator().next().getId()).getFirst();
        author2.setBookEditions(new LinkedList<BookEdition>());
        author2.getBookEditions().add(factory.getBookEditionDAO().getById(bookEdition.getId()));
        Assert.assertEquals(author, author2);
    }
}
