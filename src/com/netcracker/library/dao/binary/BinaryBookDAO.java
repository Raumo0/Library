package com.netcracker.library.dao.binary;

import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raumo0 on 20.10.16.
 */
public class BinaryBookDAO implements BookDAO {
    private static ArrayList<Book> books = new ArrayList<>();

    public BinaryBookDAO() {
//        try {
//            FileOutputStream fos = new FileOutputStream("book.out");
//        } catch (FileNotFoundException e){}
    }

    @Override
    public long insertBook(Book book) {
        try {
            FileOutputStream fos = new FileOutputStream("book.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
//        TestSerial ts = new TestSerial();
            oos.writeObject(book);
            oos.flush();
            oos.close();
        } catch (Exception e) {
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
        Book book;
        try {
            FileInputStream fis = new FileInputStream("book.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            book = (Book) oin.readObject();
            oin.close();
        } catch (Exception e) {
            return null;
        }
        return book;
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
