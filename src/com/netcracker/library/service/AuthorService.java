package com.netcracker.library.service;

import com.netcracker.library.entities.Author;
import com.netcracker.library.enums.BookCategory;

import java.util.ArrayList;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface AuthorService extends PersonService {
    ArrayList<Author> getByBookCategory(BookCategory bookCategory);
}
