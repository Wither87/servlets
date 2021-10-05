<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    </head>
    <body>
        <h1>Вход</h1>

        <form action="/login" method="post">
            Login: <input type="text" name="login"/><br>
            Password: <input type="password" name="pass"/><br>
            <input class="button" type="submit" value="Войти"/>
        </form>
        <button onclick="window.location.href = '/registration.jsp';">Регистрация</button>
    </body>
</html>