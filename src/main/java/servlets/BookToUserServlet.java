package servlets;

import entities.User;
import entities.UserBook;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserBookService;
import service.impl.UserBookServiceImpl;

import java.io.IOException;

@WebServlet("/addBookToUser")
public class BookToUserServlet extends HttpServlet {
    private final UserBookService userBookService;

    public BookToUserServlet() {
        userBookService = new UserBookServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        User user = (User) req.getSession().getAttribute("user");
        userBookService.add(new UserBook(null, Long.valueOf(bookId), user.getId()));
        req.getRequestDispatcher("/WEB-INF/data/books.jsp").forward(req, resp);
    }
}
