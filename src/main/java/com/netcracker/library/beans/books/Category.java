package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;

import java.util.LinkedList;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Category extends Entity {
    private static final long serialVersionUID = 1L;
    private String category;
    private LinkedList<BookEdition> bookEditions;

    public Category() {}

    public Category(Category category) {
        super(category);
        this.category = category.getCategory();
        this.bookEditions = category.getBookEditions();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LinkedList<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(LinkedList<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Category category1 = (Category) o;

        return category != null ? category.equals(category1.category) : category1.category == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                "} " + super.toString();
    }
}
