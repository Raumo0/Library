package com.netcracker.library.entities.business;

import com.netcracker.library.entities.Entity;

import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 15.11.16.
 */
public class IssueBook extends Entity {
    private static final long serialVersionUID = 1L;
    private IssueBook issue;
    private GregorianCalendar requestDate;
    private Rental rental;

    public IssueBook() {}

    public IssueBook getIssue() {
        return issue;
    }

    public void setIssue(IssueBook issue) {
        this.issue = issue;
    }

    public GregorianCalendar getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(GregorianCalendar requestDate) {
        this.requestDate = requestDate;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IssueBook issueBook = (IssueBook) o;

        if (issue != null ? !issue.equals(issueBook.issue) : issueBook.issue != null) return false;
        return requestDate != null ? requestDate.equals(issueBook.requestDate) : issueBook.requestDate == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (issue != null ? issue.hashCode() : 0);
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IssueBook{" +
                "issue=" + issue +
                ", requestDate=" + requestDate +
                "} " + super.toString();
    }
}
