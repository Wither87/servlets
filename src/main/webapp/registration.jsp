<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    </head>
    <body>
        <h1>Регистрация</h1>

        <form action="/registration" method="post">
			Email: <input type="text" name="email"/><br>
            Login: <input type="text" name="login"/><br>
            Password: <input type="password" name="pass"/><br>
            <input class="button" type="submit" value="Зарегистрироваться"/>
        </form>
        <button onclick="window.location.href = '/login.jsp';">Логин</button>
    </body>
</html>