package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Category extends Entity {
    private static final long serialVersionUID = 1L;
    private String category;
    private List<BookEdition> bookEditions;

    public Category() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(List<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Category category1 = (Category) o;

        if (category != null ? !category.equals(category1.category) : category1.category != null) return false;
        return bookEditions != null ? bookEditions.equals(category1.bookEditions) : category1.bookEditions == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (bookEditions != null ? bookEditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", bookEditions=" + bookEditions +
                "} " + super.toString();
    }
}