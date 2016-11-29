package com.netcracker.library.service.impl;

import com.netcracker.library.BeanFactory;
import com.netcracker.library.BeanType;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.mysql.ContextTest;
import com.netcracker.library.service.BookService;
import org.junit.*;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by raumo0 on 28.11.16.
 */
public class BookServiceImplTest {
    private static BeanFactory beanFactory;
    private static BookService bookService;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ContextTest.initializeContext();
        beanFactory = BeanFactory.getInstance();
        bookService = BookServiceImpl.getInstance();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addBookEdition() throws Exception {
        BookEdition bookEdition;
        BookEdition newBookEdition;
        bookEdition = (BookEdition) beanFactory.getBean(BeanType.BOOK_EDITION);
        int id = bookService.addBookEdition(bookEdition);
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertEquals(bookEdition, newBookEdition);
        Assert.assertEquals(bookEdition.getAuthors(), newBookEdition.getAuthors());
        Assert.assertEquals(bookEdition.getBooks(), newBookEdition.getBooks());
    }

    @Test
    public void updateBookEdition() throws Exception {

    }

    @Test
    public void deleteBookEditionById() throws Exception {

    }

    @Test
    public void getBookEditionAll() throws Exception {

    }

    @Test
    public void getBookEditionsByAuthorId() throws Exception {

    }

    @Test
    public void getBookEditionByBookId() throws Exception {

    }

    @Test
    public void getBookEditionsByGap() throws Exception {

    }

}