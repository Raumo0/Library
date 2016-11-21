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
    private static DAOFactory factory;
    private static UserDAO userDAO;
    private User user;
    private Date date = new Date();
    private int counter = 1;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        userDAO = factory.getUserDAO();
    }

    @Before
    public void setUp() throws Exception {
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
        user2 = userDAO.getById(user.getId());
        Assert.assertTrue(user.equals(user2));
        Assert.assertNotSame(user, user2);
        user2.setPassword("newPassword");
        user.setPassword("newPassword");
        userDAO.update(user2);
        Assert.assertEquals(user, userDAO.getById(user2.getId()));
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
        List<User> users = new LinkedList<>();
        User newUser;
        for (int i = 0; i < 10; i++){
            newUser = new User(user);
            newUser.setPersonId(0);
            newUser.setUsername("username" + counter + date.getTime());
            counter += 1;
            newUser.setId(userDAO.insert(newUser));
            users.add(newUser);
        }
        Assert.assertTrue(userDAO.getAll().size() == users.size());
        Assert.assertTrue(userDAO.deleteAll());
        Assert.assertTrue(userDAO.getAll().size() == 0);
    }
}