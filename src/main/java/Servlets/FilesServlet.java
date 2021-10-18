package Servlets;

import Accounts.AccountService;
import Accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/files"})
public class FilesServlet extends HttpServlet {

    private final AccountService accountService = AccountService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserProfile profile =  accountService.getUserBySessionId(req.getSession().getId());
        if (profile == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String path = req.getParameter("path");
        if (path == null || path.equals("")) {
            String userDirRedirect = "/files?path=" + accountService.getUserHomeDirectory(profile);
            resp.sendRedirect(userDirRedirect);
            return;
        }

        String absolutePath = getCanonicalPath(path);
        String userDirectoryPath = accountService.getUserHomeDirectory(profile);
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
        req.getRequestDispatcher("files.jsp").forward(req, resp);
    }

    private String getCanonicalPath(String path) throws IOException {
        return new File(path).getCanonicalPath() + '\\';
    }
}
