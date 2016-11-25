<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<form name="loginForm" method="POST" action="login">
    <input type="hidden" name="command" value="login" />
    Введите ваш логин и пароль: <br/>
    <table>
        <tr>
            <td>Логин:</td>
            <td><input type="text" name="username" value="" size="30"/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type="password" name="password" value="" size="30" /></td>
        </tr>
    </table>
    ${errorLoginOrPassword} <br />
    <input type="submit" value="Войти" />
    <a href="registration">Регистрация</a>
</form>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
