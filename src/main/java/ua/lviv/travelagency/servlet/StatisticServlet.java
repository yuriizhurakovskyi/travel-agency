package ua.lviv.travelagency.servlet;

import com.google.gson.Gson;
import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.service.BookingService;
import ua.lviv.travelagency.service.HotelService;
import ua.lviv.travelagency.service.RoomService;
import ua.lviv.travelagency.service.impl.BookingServiceImpl;
import ua.lviv.travelagency.service.impl.HotelServiceImpl;
import ua.lviv.travelagency.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/statistic")
public class StatisticServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();
    private BookingService bookingService = BookingServiceImpl.getBookingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hotelIdStr = req.getParameter("id");

        if (hotelIdStr != null) {
            Integer hotelId = Integer.parseInt(hotelIdStr);
            Map<Integer, Integer> roomsUsage = roomService.readRoomUsage(hotelId);
            req.setAttribute("roomsUsage", roomsUsage);
        }

        Map<String, Integer> mapAverageUsage = bookingService.readAverageReserveTime();
        Map<String, Integer> clientCountInHotels = roomService.readClientCount();
        List<Hotel> hotels = hotelService.readAll();

        req.setAttribute("clientCount", clientCountInHotels);
        req.setAttribute("hotels", hotels);
        req.setAttribute("averageUsage", mapAverageUsage);
        req.getRequestDispatcher("statistic.jsp").forward(req, resp);
    }
}
