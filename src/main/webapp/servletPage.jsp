<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.File"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
    <head>
        <title>First JSP</title>
    </head>
    <body>
        <div>
            <!-- Время генерации страницы -->
            <%= LocalDateTime.now() %>
        </div>
        <div>
            <form action="/logout" method="post">
                <input class="button" type="submit" value="Выйти"/>
            </form>
        </div>
        <h1>
            <!-- Теущая папка -->

        </h1>
        <hr>
        <div>
            <!-- Переход назад -->
            <!--  -->
            <% String back = (String)request.getAttribute("parent"); %>
            <% if (back != null) { %>
                <a href="/files?path=<%=back%>">
                    Назад: <%=back%>
                </a>
            <% } %>
        </div>
        <br>
        <div>
            <!-- Список всех папок -->
            <!-- -->
            <% List<File> dirs = (List<File>)request.getAttribute("dirs"); %>
                <% for (File dir : dirs){ %>
                    <p>Dir:
                        <a href="/files?path=<%=dir.getAbsolutePath()%>"><%=dir.getName()%></a>
                        <br>Date: <%=new Date(dir.lastModified())%>
                    </p>
                <% } %>
        </div>
        <div>
            <!-- Список всех файлов -->
            <!-- -->
            <% List<File> files = (List<File>)request.getAttribute("files"); %>
                <% for (File file : files){ %>
                    <p>File:
                        <a href="/download?path=<%=file.getAbsolutePath()%>"><%=file.getName()%></a>
                        <br>Size: <%=file.length()%> B
                        <br>Date: <%=new Date(file.lastModified())%>
                    </p>
                <% } %>
        </div>
    </body>
</html>