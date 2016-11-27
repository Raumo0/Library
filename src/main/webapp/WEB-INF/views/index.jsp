<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<table class="table table-bordered" border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Page count</th>
        <th>Description</th>
        <th>ISBN</th>
        <th>Weight</th>
        <th>Bookbinding</th>
    </tr>

    <c:set var="counter" value="0" scope="page"/>
    <c:set var="color" value="" />
    <c:forEach begin="1" var="book" items="${book_editions}">
        <c:set var="counter" value="${counter + 1}" scope="page"/>
        <c:if test="${counter % 2 == 0}">
            <c:set var="color" value="class=\"active\"" />
        </c:if>
        <c:if test="${counter % 4 == 0}">
            <c:set var="color" value="class=\"success\"" />
        </c:if>
        <c:if test="${counter % 6 == 0}">
            <c:set var="color" value="class=\"info\"" />
        </c:if>
        <c:if test="${counter % 8 == 0}">
            <c:set var="color" value="class=\"warning\"" />
        </c:if>
        <c:if test="${counter % 10 == 0}">
            <c:set var="color" value="class=\"danger\"" />
        </c:if>
        <tr ${color}>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.pageCount}</td>
            <td>${book.description}</td>
            <td>${book.isbn}</td>
            <td>${book.weight}</td>
            <td>${book.bookbinding}</td>
        </tr>
        <c:set var="color" value="" />
    </c:forEach>
</table>



<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table class="table" border="1" cellpadding="5" cellspacing="5">
    <ul class="pagination">
        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
            <li class="previous"><a href="/?page=${currentPage - 1}">&larr; Previous</a></li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="active"><a href="">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages}">
            <li class="next"><a href="/?page=${currentPage + 1}">Next &rarr;</a></li>
        </c:if>
    </ul>
</table>


<%@include file="/WEB-INF/jspf/footer.jspf" %>
