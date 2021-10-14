package Servlets;

import Accounts.AccountService;
import Accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final AccountService accountService = AccountService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        if (!loginIsCorrect(login)
                || !passIsCorrect(pass)
                || !emailIsCorrect(email) ){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendRedirect("/registration");
            return;
        }



        Path userDirectoryPath = Paths.get(accountService.getHomeDirectory() + login);
        if (accountService.getUserByLogin(login) != null || Files.exists(userDirectoryPath)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Пользователь с логином уже существует");
            //resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Files.createDirectory(userDirectoryPath);
        UserProfile profile = new UserProfile(login, pass, email);
        accountService.addNewUser(profile);
        accountService.addSession(req.getSession().getId(), profile);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/files");
    }

    private boolean loginIsCorrect(String login){
        return login == null || login.equals("");
    }
    private boolean passIsCorrect(String pass){
        return pass  == null || pass.equals("");
    }
    private boolean emailIsCorrect(String email){
        return email == null || email.equals("");
    }
}
