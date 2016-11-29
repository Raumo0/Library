<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

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
    <link type="text/css" href="../../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link type="text/css" href="../../resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <%--<link type="text/css" href="resources/css/style.css" rel="stylesheet">--%>
    <link type="text/css" href="../../resources/css/style.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <form class="form-signin" name="loginForm" method="POST" action="login">
        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        ${errorLogin}
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a class="btn btn-default btn-block" href="registration" role="button">Create an account</a>
    </form>
</div>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
