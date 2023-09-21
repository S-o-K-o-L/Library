package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/hello")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        Integer visitCounter = (Integer) httpSession.getAttribute("visitSession");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        httpSession.setAttribute("visitSession", visitCounter);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        if (username == null) {
            printWriter.write("Hello anon <br>");
        } else {
            printWriter.write("Hello " + username + " <br>");
        }
        printWriter.write("Page was visted " + visitCounter + " times.");
        printWriter.close();
    }
}
