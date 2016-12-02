<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<div class="jumbotron">
    <h1>Book: </h1>
    <h2>Title : ${book_edition_title}</h2>
    <br>
    <p>Page count : ${book_edition_page_count}</p>
    <br>
    <p>Description : ${book_edition_description}</p>
    <br>
    <p>isbn : ${book_edition_isbn}</p>
    <br>
    <p>Bookbinding : ${book_edition_bookbinding}</p>
    <br>
    <p>Count in store : ${book_edition_book_count}</p>

    <c:if test="${book_edition_book_count > 0}">
        <button type="button" class="btn btn-sm btn-success">Get book</button>
    </c:if>
</div>
</body>
</html>
