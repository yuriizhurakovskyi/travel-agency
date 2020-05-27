package ua.lviv.travelagency.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            req.setAttribute("userLogged", "no");
        } else {
            req.setAttribute("userLogged", "yes");
        }
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
