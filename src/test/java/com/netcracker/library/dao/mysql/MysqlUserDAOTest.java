package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;
import com.netcracker.library.enums.*;
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
        user.setRole(UserRole.ADMIN);
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
        user2.setRole(UserRole.READER);
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
    public void getUsersByRole() throws Exception {
        userDAO.deleteAll();

        user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        user.setUsername("username" + counter + date.getTime());
        user.setPassword("password");
        user.setSalt("salt");
        user.setRole(UserRole.ADMIN);
        user.setId(userDAO.insert(user));
        counter += 1;

        User user2 = new User();
        user2.setFirstName("Mike");
        user2.setLastName("Mickelson");
        user2.setUsername("username" + counter + date.getTime());
        user2.setPassword("password");
        user2.setSalt("salt");
        user2.setRole(UserRole.LIBRARIAN);
        user2.setId(userDAO.insert(user2));
        counter += 1;

        User user3 = new User();
        user3.setFirstName("Mike");
        user3.setLastName("Mickelson");
        user3.setUsername("username" + counter + date.getTime());
        user3.setPassword("password");
        user3.setSalt("salt");
        user3.setRole(UserRole.READER);
        user3.setId(userDAO.insert(user3));
        counter += 1;

        User user4 = new User();
        user4.setFirstName("Mike");
        user4.setLastName("Mickelson");
        user4.setUsername("username" + counter + date.getTime());
        user4.setPassword("password");
        user4.setSalt("salt");
        user4.setRole(UserRole.READER);
        user4.setId(userDAO.insert(user4));
        counter += 1;

        LinkedList<User> users = new LinkedList<>();
        LinkedList<User> users2 = new LinkedList<>();
        LinkedList<User> users3 = new LinkedList<>();
        users.add(user);
        users2.add(user2);
        users3.add(user3);
        users3.add(user4);

        LinkedList<User> result1 = userDAO.getUsersByRole(UserRole.ADMIN);
        LinkedList<User> result2 = userDAO.getUsersByRole(UserRole.LIBRARIAN);
        LinkedList<User> result3 = userDAO.getUsersByRole(UserRole.READER);
        Assert.assertEquals(users, result1);
        Assert.assertEquals(users2, result2);
        Assert.assertEquals(users3, result3);
    }
}