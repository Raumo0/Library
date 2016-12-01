package com.netcracker.library.services.impl;

import com.netcracker.library.BeanFactory;
import com.netcracker.library.BeanType;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.mysql.ContextTest;
import com.netcracker.library.enums.Bookbinding;
import com.netcracker.library.services.BookService;
import org.junit.*;

import java.util.Collection;

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
        BookEdition bookEdition;
        BookEdition newBookEdition;
        bookEdition = (BookEdition) beanFactory.getBean(BeanType.BOOK_EDITION);
        int id = bookService.addBookEdition(bookEdition);
        bookEdition.setTitle("newTitle");
        bookEdition.setPageCount(192);
        bookEdition.setDescription("newDescription");
        bookEdition.setWeight(935);
        bookEdition.setBookbinding(Bookbinding.SOFT);
        Assert.assertTrue(bookService.updateBookEdition(bookEdition));
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertEquals(bookEdition, newBookEdition);
        Assert.assertEquals(bookEdition.getAuthors(), newBookEdition.getAuthors());
        Assert.assertEquals(bookEdition.getBooks(), newBookEdition.getBooks());
    }

    @Test
    public void deleteBookEditionById() throws Exception {
        BookEdition bookEdition;
        BookEdition newBookEdition;
        bookEdition = (BookEdition) beanFactory.getBean(BeanType.BOOK_EDITION);
        int id = bookService.addBookEdition(bookEdition);
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertEquals(bookEdition, newBookEdition);
        Assert.assertEquals(bookEdition.getAuthors(), newBookEdition.getAuthors());
        Assert.assertEquals(bookEdition.getBooks(), newBookEdition.getBooks());
        newBookEdition = null;
        Assert.assertTrue(bookService.deleteBookEditionById(id));
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertNull(newBookEdition);
    }

    @Test
    public void getBookEditionAll() throws Exception {

    }

    @Test
    public void getBookEditionsByAuthorId() throws Exception {
        Author author;
        Collection<BookEdition> bookEditions;
        author = (Author) beanFactory.getBean(BeanType.AUTHOR);
        bookService.addAuthor(author);

        bookEditions = bookService.getBookEditionsByAuthorId(author.getId());
        Assert.assertEquals(author.getBookEditions(), bookEditions);
    }

    @Test
    public void getBookEditionByBookId() throws Exception {

    }

    @Test
    public void getBookEditionsByGap() throws Exception {

    }

}