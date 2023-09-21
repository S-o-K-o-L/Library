package servlets;

import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.findByLogin(login, password);
            req.getSession().setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/profile.jsp");
            requestDispatcher.forward(req,resp);
        } catch (FindEntityException e) {
            req.setAttribute("error", true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
            requestDispatcher.include(req,resp);
        }
    }
}
