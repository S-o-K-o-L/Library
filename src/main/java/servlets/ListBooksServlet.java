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
import java.util.List;

@WebServlet("/data/books")
public class ListBooksServlet extends HttpServlet {
    private final BookService bookService;

    public ListBooksServlet() {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Book> bookList = bookService.findAll();
        req.getSession().setAttribute("bookList", bookList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/data/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/profile.jsp");
        requestDispatcher.forward(req, resp);
    }
}
