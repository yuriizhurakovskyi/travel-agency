package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hotel> hotels = (List<Hotel>) req.getSession().getAttribute("hotels");
        req.setAttribute("hotels", hotels);
        req.getRequestDispatcher("WEB-INF/result.jsp").forward(req, resp);
    }
}
