<%@ page import="java.time.LocalDateTime" %>
<%@ page import="code.FileSystemReader" %>
<%
    FileSystemReader fsr = new FileSystemReader(request.getParameter("path"));
%>
<!doctype html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>First JSP</title>
    </head>
    <body>
        <div>
            <!-- Время генерации страницы -->
            <%= LocalDateTime.now() %>
        </div>
        <h1>
            <!-- Теущая папка -->
            <%= fsr.getPath() %>
        </h1>
        <hr>
        <div>
            <!-- Переход назад -->
            <%= fsr.getBackPath() %>
        </div>
        <br>
        <div>
            <!-- Список всех папок -->
            <%= fsr.getDirectories() %>
        </div>
        <div>
            <!-- Список всех файлов -->
            <%= fsr.getFiles() %>
        </div>
    </body>
</html>