package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.Role;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;
import com.netcracker.library.enums.BookIssue;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.Bookbinding;
import org.junit.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void getUserByPersonId() throws Exception {
        Assert.assertEquals(user, userDAO.getUserByPersonId(user.getPersonId()));
    }

    @Test
    public void getUserByRentalId() throws Exception {
        BookEdition bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn(new Random().nextInt());
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(factory.getBookEditionDAO().insert(bookEdition));

        Book book = new Book();
        book.setBookEdition(bookEdition);
        book.setBookPosition(BookPosition.STORE);
        book.setBookState(BookState.EXCELLENT);
        book.setId(factory.getBookDAO().insert(book));

        Rental rental = new Rental();
        rental.setComment("commentary");
        rental.setUser(user);
        rental.setBook(book);
        rental.setStateBefore(book.getBookState());
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(factory.getRentalDAO().insert(rental));

        Assert.assertEquals(user, userDAO.getUserByRentalId(rental.getId()));
    }

    @Test
    public void getStaffUserByRentalId() throws Exception {
        BookEdition bookEdition = new BookEdition();
        bookEdition.setTitle("title");
        bookEdition.setPageCount(543);
        bookEdition.setDescription("description");
        bookEdition.setIsbn(new Random().nextInt());
        bookEdition.setWeight(1520);
        bookEdition.setBookbinding(Bookbinding.HARD);
        bookEdition.setId(factory.getBookEditionDAO().insert(bookEdition));

        Book book = new Book();
        book.setBookEdition(bookEdition);
        book.setBookPosition(BookPosition.STORE);
        book.setBookState(BookState.EXCELLENT);
        book.setId(factory.getBookDAO().insert(book));

        User user2 = new User();
        user2.setFirstName("Mike");
        user2.setLastName("Mickelson");
        user2.setUsername("username" + counter + date.getTime());
        user2.setPassword("password");
        user2.setSalt("salt");
        user2.setId(userDAO.insert(user2));
        counter += 1;

        Rental rental = new Rental();
        rental.setComment("commentary");
        rental.setUser(user);
        rental.setStaff_user(user2);
        rental.setBook(book);
        rental.setStateBefore(book.getBookState());
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(factory.getRentalDAO().insert(rental));

        Assert.assertEquals(user2, userDAO.getStaffUserByRentalId(rental.getId()));
    }

    @Test
    public void getUsersByRoleId() throws Exception {
        Role role = new Role();
        role.setName("role_name" + counter + date.getTime());
        counter += 1;
        role.setDescription("description");
        role.setId(factory.getRoleDAO().insert(role));

        Role role2 = new Role();
        role2.setName("role_name" + counter + date.getTime());
        counter += 1;
        role2.setDescription("description");
        role2.setId(factory.getRoleDAO().insert(role2));

        User user2 = new User();
        user2.setFirstName("Mike");
        user2.setLastName("Mickelson");
        user2.setUsername("username" + counter + date.getTime());
        user2.setPassword("password");
        user2.setSalt("salt");
        user2.setId(userDAO.insert(user2));
        counter += 1;

        User user3 = new User();
        user3.setFirstName("Mike");
        user3.setLastName("Mickelson");
        user3.setUsername("username" + counter + date.getTime());
        user3.setPassword("password");
        user3.setSalt("salt");
        user3.setId(userDAO.insert(user3));
        counter += 1;

        user.setRole(role);
        userDAO.update(user);
        user2.setRole(role);
        userDAO.update(user2);
        user3.setRole(role2);
        userDAO.update(user3);
        LinkedList<User> users = new LinkedList<>();
        LinkedList<User> users2 = new LinkedList<>();
        users.add(user);
        users.add(user2);
        users2.add(user3);

        LinkedList<User> result1 = userDAO.getUsersByRoleId(user.getRole().getId());
        LinkedList<User> result2 = userDAO.getUsersByRoleId(user2.getRole().getId());
        LinkedList<User> result3 = userDAO.getUsersByRoleId(user3.getRole().getId());
        user.setRole(null);
        user2.setRole(null);
        user3.setRole(null);
        Assert.assertEquals(users, result1);
        Assert.assertEquals(users, result2);
        Assert.assertEquals(users2, result3);
    }
}