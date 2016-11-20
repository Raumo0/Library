package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;
import org.junit.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 20.11.16.
 */
public class MysqlUserDAOTest {
    private DAOFactory factory;
    private UserDAO userDAO;
    private User user;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
    }

    @Before
    public void setUp() throws Exception {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        userDAO = factory.getUserDAO();
        user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        user.setUsername("username" + counter + date.getTime());
        user.setPassword("password");
        user.setSalt("salt");
        user.setId(userDAO.insert(user));
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        factory = null;
        userDAO = null;
        user = null;
    }

    @Test
    public void insert() throws Exception {
        User user2;
        user2 = userDAO.getById(user.getId());
        Assert.assertEquals(user, user2);
    }

    @Test
    public void update() throws Exception {
        User user2;
        user2 = new User(user);
        boolean a = user.equals(user2);
        Assert.assertTrue(a);
        user2.setPassword("newPassword");
        userDAO.update(user2);
        Assert.assertFalse(user.equals(user2));
    }

    @Test
    public void deleteById() throws Exception {
        User user2;
        boolean deleted = userDAO.deleteById(user.getId());
        Assert.assertTrue(deleted);
        user2 = userDAO.getById(user.getId());
        Assert.assertTrue(!user.equals(user2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(userDAO.deleteAll());
        Assert.assertTrue(userDAO.getAll().size() == 0);
        List<User> authors = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            User newAuthor = new User(user);
            newAuthor.setPersonId(0);
            user.setUsername("username" + counter + date.getTime());
            counter += 1;
            newAuthor.setId(userDAO.insert(newAuthor));
            authors.add(newAuthor);
        }
        Assert.assertTrue(userDAO.getAll().size() == authors.size());
        Assert.assertTrue(userDAO.deleteAll());
        Assert.assertTrue(userDAO.getAll().size() == 0);
    }
}