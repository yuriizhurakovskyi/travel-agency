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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/hotelDetails")
public class HotelDetailServlet extends HttpServlet {
    private RoomService roomService = RoomServiceImpl.getRoomServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hotelId = Integer.parseInt(req.getParameter("id"));
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");
        java.sql.Date startDateSql = null;
        java.sql.Date endDateSql = null;
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(startDateStr);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDateStr);
            startDateSql = new java.sql.Date(startDate.getTime());
            endDateSql = new java.sql.Date(endDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Room> rooms = roomService.readRoomsByHotelAndDates(hotelId, startDateSql, endDateSql);

        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("hotelDetails.jsp").forward(req, resp);
    }
}
