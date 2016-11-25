<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%--<%@include file="/WEB-INF/jspf/header.jspf" %>--%>

<%--<form name="registrationForm" method="POST" action="registration">--%>
    <%--<input type="hidden" name="command" value="registration" />--%>
    <%--Введите ваши данные:<br/>--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td>Имя:</td>--%>
            <%--<td><input type="text" name="firstname" value="" size="20"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Фамилия:</td>--%>
            <%--<td><input type="text" name="lastname" value="" size="20" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Логин:</td>--%>
            <%--<td><input type="text" name="username" value="" size="20"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Пароль:</td>--%>
            <%--<td><input type="password" name="password" value="" size="20" /></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--${operationMessage}--%>
    <%--${errorUserExsists} <br />--%>
    <%--<input type="submit" value="Зарегистрировать" />--%>
<%--</form>--%>

<%--<%@include file="/WEB-INF/jspf/footer.jspf" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin</title>

    <!-- Bootstrap core CSS -->
    <link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link type="text/css" href="resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link type="text/css" href="resources/css/style.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <form class="form-signin" name="registrationForm" method="POST" action="registration">
        <h2 class="form-signin-heading">Please set your data</h2>

        <label for="firstname" class="sr-only">First name</label>
        <input type="text" name="firstname" id="firstname" class="form-control" placeholder="First name" required autofocus>

        <label for="lastname" class="sr-only">Last name</label>
        <input type="text" name="lastname" id="lastname" class="form-control" placeholder="Last name" required autofocus>

        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create an account</button>
    </form>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>