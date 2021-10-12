package Servlets;

import Accounts.AccountService;
import Accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/files"})
public class FilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserProfile profile =  AccountService.getUserBySessionId(req.getSession().getId());
        if (profile == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String path = req.getParameter("path");
        String userDirectoryPath = AccountService.getHomeDirectory() + profile.getLogin();
        if (path == null || path.equals("")) {
            path = userDirectoryPath;
        }
        String absolutePath = Paths.get(path).toAbsolutePath().toString();
        if (!absolutePath.startsWith(userDirectoryPath)){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        FileSystemReader fsr = new FileSystemReader(absolutePath, userDirectoryPath);
        req.setAttribute("dirs", fsr.getDirectories());
        req.setAttribute("files", fsr.getFiles());
        req.setAttribute("parent", fsr.getParentDirectory());
        req.setAttribute("path", fsr.getPath());
        req.getRequestDispatcher("servletPage.jsp").forward(req, resp);
    }
}
