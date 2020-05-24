package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.service.RoomService;
import ua.lviv.travelagency.service.impl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hotelDetails")
public class HotelDetailServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hotelId = Integer.parseInt(req.getParameter("id"));
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");
        java.sql.Date startDateSql = SearchHotelByCityAndDateServlet.dateInSql(startDateStr);
        java.sql.Date endDateSql = SearchHotelByCityAndDateServlet.dateInSql(endDateStr);

        List<Room> rooms = roomService.readRoomsByHotelAndDates(hotelId, startDateSql, endDateSql);

        req.setAttribute("rooms", rooms);
        req.setAttribute("startDate", startDateStr);
        req.setAttribute("endDate", endDateStr);
        req.getRequestDispatcher("hotelDetails.jsp").forward(req, resp);
    }
}
