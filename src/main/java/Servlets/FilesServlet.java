package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/files"})
public class FilesServlet extends HttpServlet {

    private final String defaultPath = "D:\\test\\";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null){
            path = defaultPath;
        }
        FileSystemReader fsr = new FileSystemReader(path);
        req.setAttribute("dirs", fsr.getDirectories());
        req.setAttribute("files", fsr.getFiles());
        req.setAttribute("parent", fsr.getParentDirectory());
        req.getRequestDispatcher("servletPage.jsp").forward(req, resp);
    }
}
