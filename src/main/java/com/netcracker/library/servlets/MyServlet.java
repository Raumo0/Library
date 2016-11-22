package com.netcracker.library.servlets;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by raumo0 on 18.11.16.
 */
@WebServlet("/s")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fName");
        String lastName = request.getParameter("lName");
        String biography = request.getParameter("bio");

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBiography(biography);
        try {
            author.setId(BookServiceImpl.getInstance().addAuthor(author));
            author = BookServiceImpl.getInstance().getAuthorById(author.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");

        String varTextA = "Hello World!";
        request.setAttribute("textA", varTextA);
        String varTextB = "not working =(";
        if (author != null)
            varTextB = author.toString();
        request.setAttribute("textB", varTextB);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
