package com.netcracker.library.service;

import com.netcracker.library.entities.Author;
import com.netcracker.library.enums.BookCategory;

import java.util.Collection;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface AuthorService extends PersonService {
    long insertAuthor(Author author);

    Collection<Author> getAuthors();

    Collection<Author> getByBookCategory(BookCategory bookCategory);
}
