<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

REGISTRATION


<form name="registrationForm" method="POST" action="registration">
    <input type="hidden" name="command" value="registration" />
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="firstname" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Фамилия:</td>
            <td><input type="text" name="lastname" value="" size="20" /></td>
        </tr>
        <tr>
            <td>Логин:</td>
            <td><input type="text" name="username" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type="password" name="password" value="" size="20" /></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorUserExsists} <br />
    <input type="submit" value="Зарегистрировать" />
    <a href="controller?command=back">Вернуться обратно</a>
</form>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
