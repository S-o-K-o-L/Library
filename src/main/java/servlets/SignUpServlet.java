package servlets;

import entities.User;
import exceptions.AddEntityException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/auth/signup")
public class SignUpServlet extends HttpServlet {
    private final UserService userService;

    public SignUpServlet() {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/auth/signup.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        try {
            userService.create(new User(null, firstName, lastName, phone, login, password));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
            requestDispatcher.forward(req,resp);
        } catch (AddEntityException e) {
            req.setAttribute("error", true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/auth/signup.jsp");
            requestDispatcher.include(req,resp);
        }
    }
}
