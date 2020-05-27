package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.service.HotelService;
import ua.lviv.travelagency.service.impl.HotelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hotel> hotels = hotelService.readAll();
        req.setAttribute("hotels", hotels);
        req.getRequestDispatcher("WEB-INF/manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
