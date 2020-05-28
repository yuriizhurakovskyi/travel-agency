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

@WebServlet("/updateHotel")
public class UpdateHotelServlet extends HttpServlet {
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hotelId = Integer.parseInt(req.getParameter("hotelId"));
        Hotel hotel = hotelService.read(hotelId);
        req.setAttribute("hotel", hotel);
        req.getRequestDispatcher("WEB-INF/updateHotel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("hotelName");
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        Integer hotel_id = Integer.parseInt(req.getParameter("hotelId"));
        Hotel hotel = new Hotel(hotel_id, name, rating, country, city, 0, 1);
        hotelService.update(hotel);
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            req.setAttribute("userLogged", "no");
        } else req.setAttribute("userLogged", "yes");
        resp.sendRedirect("manager");
    }
}
