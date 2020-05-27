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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addroom")
public class RoomServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hotelId = null;
        if (req.getSession().getAttribute("hotelId") != null)
            hotelId = Integer.parseInt(req.getSession().getAttribute("hotelId").toString());
        if (req.getParameter("hotelId") != null) {
            hotelId = Integer.parseInt(req.getParameter("hotelId"));
            req.getSession().setAttribute("hotelId", hotelId);
        }
        Hotel hotel = hotelService.read(hotelId);
        req.setAttribute("hotel", hotel);
        req.getRequestDispatcher("addroom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer capacity = Integer.parseInt(req.getParameter("capacity"));
        String type = (req.getParameter("type"));
        Boolean wifi = req.getParameter("wifi").equals("yes");
        Boolean breakfast = req.getParameter("breakfast").equals("yes");
        Double price = Double.parseDouble(req.getParameter("price"));
        HttpSession session = req.getSession();
        Integer hotel_id = (Integer) session.getAttribute("hotelId");
        Room room = new Room(capacity, type, wifi, breakfast, price, hotel_id);
        Hotel hotel = hotelService.read(hotel_id);
        Integer roomCount = hotel.getRoom_count();
        hotel.setRoom_count(++roomCount);
        hotelService.update(hotel);
        roomService.create(room);
        resp.sendRedirect("addroom");
    }
}
