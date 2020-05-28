package ua.lviv.travelagency.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            req.setAttribute("userLogged", "no");
        } else req.setAttribute("userLogged", "yes");
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
