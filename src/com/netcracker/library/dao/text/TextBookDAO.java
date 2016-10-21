package com.netcracker.library.dao.text;

import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.tools.TextFileWorker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raumo0 on 20.10.16.
 */
public class TextBookDAO implements BookDAO {
    private static String text;
    private static String fileName = "books.txt";
    private static ArrayList<Book> books = new ArrayList<>();

    public TextBookDAO() {
        try {
            this.text = TextFileWorker.read(fileName);
        } catch (FileNotFoundException e) {
            text = "";
        }
    }

    @Override
    public long insertBook(Book book) {
        text = text.concat(book.toString());
        try {
            TextFileWorker.write(fileName, text);
        } catch (RuntimeException e) {
            return -1;
        }
        return book.getId();
    }

    @Override
    public boolean deleteBook(long id) {
        return false;
    }

    @Override
    public Book findBook(long id) {
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public Collection selectBooksByState(BookState state) {
        return null;
    }

    @Override
    public Collection selectBooksByPosition(BookPosition position) {
        return null;
    }
}
