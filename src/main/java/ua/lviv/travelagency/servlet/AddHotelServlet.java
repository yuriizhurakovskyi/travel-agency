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

@WebServlet("/addhotel")
public class AddHotelServlet extends HttpServlet {
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addhotel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("hotelName");
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        Hotel hotel = new Hotel(name, rating, country, city, 0, 1);
        hotelService.create(hotel);
        resp.sendRedirect("addhotel");
    }
}
