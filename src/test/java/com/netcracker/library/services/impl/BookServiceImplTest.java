package com.netcracker.library.services.impl;

import com.netcracker.library.BeanFactory;
import com.netcracker.library.BeanType;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.mysql.ContextTest;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.Bookbinding;
import com.netcracker.library.services.BookService;
import org.junit.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertFalse(bookEdition.equals(newBookEdition));
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
        Assert.assertTrue(bookService.deleteBookEditionById(id));
        newBookEdition = bookService.getBookEditionById(id);
        Assert.assertNull(newBookEdition);
    }

    @Test
    public void getBookEditionAll() throws Exception {
        //todo
    }

    @Test
    public void deleteBookEditionAll() throws Exception {
        //todo
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
        Book book;
        BookEdition bookEdition;
        book = (Book) beanFactory.getBean(BeanType.BOOK);
        bookService.addBook(book);

        bookEdition = bookService.getBookEditionByBookId(book.getId());
        Assert.assertEquals(book.getBookEdition(), bookEdition);
    }

    @Test
    public void getBookEditionsByGap() throws Exception {
        List<BookEdition> editions = new LinkedList<>();
        BookEdition edition;
        Assert.assertTrue(bookService.deleteBookEditionAll());
        for (int i = 0; i < 10; i++){
            edition = (BookEdition) beanFactory.getBean(BeanType.BOOK_EDITION);
            bookService.addBookEdition(edition);
            editions.add(edition);
        }
        Assert.assertEquals(bookService.getBookEditionsByGap(0, 3), editions.subList(0, 0 + 3));
        Assert.assertEquals(bookService.getBookEditionsByGap(3, 5), editions.subList(3, 3 + 5));
        Assert.assertEquals(bookService.getBookEditionsByGap(2, 8), editions.subList(2, 2 + 8));
        Assert.assertEquals(bookService.getBookEditionsByGap(6, 7), editions.subList(6, editions.size()));
    }

    @Test
    public void numberOfBookEditionRecords() throws Exception {
        Assert.assertTrue(bookService.deleteBookEditionAll());
        Assert.assertTrue(bookService.numberOfBookEditionRecords() == 0);
        List<BookEdition> bookEditions = new LinkedList<>();
        BookEdition edition;
        for (int i = 0; i < 10; i++){
            edition = (BookEdition) beanFactory.getBean(BeanType.BOOK_EDITION);
            bookService.addBookEdition(edition);
            bookEditions.add(edition);
        }
        Assert.assertTrue(bookService.numberOfBookEditionRecords() == 10);
    }

    @Test
    public void addBook() throws Exception {
        Book book;
        Book newBook;
        book = (Book) beanFactory.getBean(BeanType.BOOK);
        int id = bookService.addBook(book);
        newBook = bookService.getBookById(id);
        Assert.assertEquals(book, newBook);
        Assert.assertEquals(book.getRentals(), newBook.getRentals());
        Assert.assertEquals(book.getBookEdition(), newBook.getBookEdition());
    }

    @Test
    public void updateBook() throws Exception {
        Book book;
        Book newBook;
        book = (Book) beanFactory.getBean(BeanType.BOOK);
        int id = bookService.addBook(book);
        book.setBookState(BookState.BAD);
        newBook = bookService.getBookById(id);
        Assert.assertFalse(book.equals(newBook));
        Assert.assertTrue(bookService.updateBook(book));
        newBook = bookService.getBookById(id);
        Assert.assertEquals(book, newBook);
        Assert.assertEquals(book.getRentals(), newBook.getRentals());
    }

    @Test
    public void deleteBookById() throws Exception {
        Book book;
        Book newBook;
        book = (Book) beanFactory.getBean(BeanType.BOOK);
        int id = bookService.addBook(book);
        newBook = bookService.getBookById(id);
        Assert.assertEquals(book, newBook);
        Assert.assertEquals(book.getRentals(), newBook.getRentals());
        Assert.assertTrue(bookService.deleteBookById(id));
        newBook = bookService.getBookById(id);
        Assert.assertNull(newBook);
    }

    @Test
    public void getBookAll() throws Exception {
        //todo
    }

    @Test
    public void deleteBookAll() throws Exception {
        //todo
    }

    @Test
    public void getBookByRentalId() throws Exception {
        Book book;
        Book newBook;
        book = (Book) beanFactory.getBean(BeanType.BOOK);
        bookService.addBook(book);
        newBook = bookService.getBookByRentalId(book.getRentals().iterator().next().getId());
        Assert.assertEquals(book, newBook);
        Assert.assertEquals(book.getRentals(), newBook.getRentals());
    }

    @Test
    public void getBooksByBookEditionId() throws Exception {
        Book book;
        Book newBook = null;
        Collection<Book> books;
        boolean flag = false;

        book = (Book) beanFactory.getBean(BeanType.BOOK);
        bookService.addBook(book);
        books = bookService.getBooksByBookEditionId(book.getBookEdition().getId());

        for (Book myBook : books){
            if (book.equals(myBook)){
                flag = true;
                newBook = myBook;
            }
        }
        Assert.assertTrue(flag);
        Assert.assertEquals(book, newBook);
        Assert.assertEquals(book.getRentals(), newBook.getRentals());
    }

    @Test
    public void addAuthor() throws Exception {
        Author author;
        Author newAuthor;
        author = (Author) beanFactory.getBean(BeanType.AUTHOR);
        int id = bookService.addAuthor(author);
        newAuthor = bookService.getAuthorById(id);
        Assert.assertEquals(author, newAuthor);
        Assert.assertEquals(author.getBookEditions(), newAuthor.getBookEditions());
    }

    @Test
    public void updateAuthor() throws Exception {
        Author author;
        Author newAuthor;
        author = (Author) beanFactory.getBean(BeanType.AUTHOR);
        int id = bookService.addAuthor(author);
        author.setBiography("new biography");
        newAuthor = bookService.getAuthorById(id);
        Assert.assertFalse(author.equals(newAuthor));
        Assert.assertTrue(bookService.updateAuthor(author));
        newAuthor = bookService.getAuthorById(id);
        Assert.assertEquals(author, newAuthor);
        Assert.assertEquals(author.getBookEditions(), newAuthor.getBookEditions());
    }

    @Test
    public void deleteAuthorById() throws Exception {

    }

    @Test
    public void getAuthorAll() throws Exception {

    }

    @Test
    public void deleteAuthorAll() throws Exception {

    }

    @Test
    public void getAuthorByPersonId() throws Exception {

    }

    @Test
    public void getAuthorsByBookEditionId() throws Exception {

    }
}