package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.RentalDAO;
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
 * Created by raumo0 on 21.11.16.
 */
public class MysqlRentalDAOTest {
    private static DAOFactory factory;
    private static RentalDAO rentalDAO;
    private static Book book;
    private static User user;
    private static int counter = 1;
    private static Date date = new Date();
    private Rental rental;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        rentalDAO = factory.getRentalDAO();
        BookEdition bookEdition = new BookEdition();
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
        book.setId(factory.getBookDAO().insert(book));

        user = new User();
        user.setFirstName("Mike");
        user.setLastName("Mickelson");
        user.setUsername("username" + counter + date.getTime());
        counter += 1;
        user.setPassword("password");
        user.setSalt("salt");
        user.setId(factory.getUserDAO().insert(user));
    }

    @Before
    public void setUp() throws Exception {
        rental = new Rental();
        rental.setComment("commentary");
        rental.setUser(user);
        rental.setBook(book);
        rental.setStateBefore(book.getBookState());
        rental.setBookIssue(BookIssue.ORDERED);
        rental.setId(rentalDAO.insert(rental));
    }

    @After
    public void tearDown() throws Exception {
        rental = null;
    }

    @Test
    public void insert() throws Exception {
        Rental rental2;
        rental2 = rentalDAO.getById(rental.getId());
        Assert.assertEquals(rental, rental2);
    }

    @Test
    public void update() throws Exception {
        Rental rental2;
        rental2 = rentalDAO.getById(rental.getId());
        Assert.assertTrue(rental.equals(rental2));
        Assert.assertNotSame(rental, rental2);
        rental2.setStateAfter(BookState.BAD);
        rental.setStateAfter(BookState.BAD);
        rentalDAO.update(rental2);
        Assert.assertEquals(rental, rentalDAO.getById(rental2.getId()));
    }

    @Test
    public void deleteById() throws Exception {
        Rental rental2;
        boolean deleted = rentalDAO.deleteById(rental.getId());
        Assert.assertTrue(deleted);
        rental2 = rentalDAO.getById(rental.getId());
        Assert.assertTrue(!rental.equals(rental2));
    }

    @Test
    public void deleteAll() throws Exception {
        Assert.assertTrue(rentalDAO.deleteAll());
        Assert.assertTrue(rentalDAO.getAll().size() == 0);
        List<Rental> rentals = new LinkedList<>();
        Rental rental2;
        for (int i = 0; i < 10; i++){
            rental2 = new Rental(rental);
            rental2.setId(rentalDAO.insert(rental2));
            rentals.add(rental2);
        }
        Assert.assertTrue(rentalDAO.getAll().size() == rentals.size());
        Assert.assertTrue(rentalDAO.deleteAll());
        Assert.assertTrue(rentalDAO.getAll().size() == 0);
    }

}