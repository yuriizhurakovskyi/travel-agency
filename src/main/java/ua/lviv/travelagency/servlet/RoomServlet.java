package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.service.HotelService;
import ua.lviv.travelagency.service.RoomService;
import ua.lviv.travelagency.service.impl.HotelServiceImpl;
import ua.lviv.travelagency.service.impl.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Integer capacity = Integer.parseInt(req.getParameter("capacity"));
        String type = (req.getParameter("type"));
        Boolean wifi = req.getParameter("wifi").equals("true");
        Boolean breakfast = req.getParameter("breakfast").equals("true");
        Double price = Double.parseDouble(req.getParameter("price"));
        Integer hotel_id = Integer.parseInt(req.getParameter("hotel_id"));

        Room room = new Room(capacity, type, wifi, breakfast, price, hotel_id);

        Hotel hotel = hotelService.read(hotel_id);
        Integer roomCount = hotel.getRoom_count();
        hotel.setRoom_count(++roomCount);
        hotelService.update(hotel);

        roomService.create(room);
    }
}
