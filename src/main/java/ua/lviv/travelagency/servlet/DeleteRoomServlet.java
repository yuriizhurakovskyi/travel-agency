package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.service.HotelService;
import ua.lviv.travelagency.service.RoomService;
import ua.lviv.travelagency.service.impl.HotelServiceImpl;
import ua.lviv.travelagency.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer roomID = Integer.parseInt(req.getParameter("roomId"));
        Integer hotelID = Integer.parseInt(req.getParameter("hotelId"));
        roomService.delete(roomID);
        List<Room> rooms = roomService.readRoomsByHotelId(hotelID);
        Hotel hotel = hotelService.read(hotelID);
        int roomCount = hotel.getRoom_count() - 1;
        if (roomCount >= 0)
            hotel.setRoom_count(roomCount);
        hotelService.update(hotel);
        req.setAttribute("rooms", rooms);
        req.setAttribute("hotel", hotel);
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            req.setAttribute("userLogged", "no");
        } else req.setAttribute("userLogged", "yes");
        req.getRequestDispatcher("WEB-INF/managerooms.jsp").forward(req, resp);
    }
}
