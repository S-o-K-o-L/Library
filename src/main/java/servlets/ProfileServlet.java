package servlets;

import entities.Book;
import entities.User;
import entities.UserBook;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookService;
import service.UserBookService;
import service.impl.BookServiceImpl;
import service.impl.UserBookServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {
    private final UserBookService userBookService;
    private final BookService bookService;

    public ProfileServlet() {
        this.userBookService = new UserBookServiceImpl();
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<UserBook> bookListForUser = userBookService.findBooksForUser(user);
        List<Book> bookList = bookListForUser
                .stream()
                .map(userBook -> bookService.findById(userBook.getIdBook()))
                .toList();
        req.getSession().setAttribute("userBooks",bookList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/profile.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/data/books.jsp");
        requestDispatcher.forward(req, resp);
    }
}
