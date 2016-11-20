package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Language extends Entity {
    private static final long serialVersionUID = 1L;
    private String language;
    private List<BookEdition> bookEditions;

    public Language() {}

    public Language(Language language) {
        super(language);
        this.language = language.getLanguage();
        this.bookEditions = language.getBookEditions();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

        Language language1 = (Language) o;

        if (language != null ? !language.equals(language1.language) : language1.language != null) return false;
        return bookEditions != null ? bookEditions.equals(language1.bookEditions) : language1.bookEditions == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (bookEditions != null ? bookEditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language='" + language + '\'' +
                ", bookEditions=" + bookEditions +
                "} " + super.toString();
    }
}
