package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.service.BookingService;
import ua.lviv.travelagency.service.impl.BookingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@WebServlet("/reserveRoom")
public class ReserveRoomServlet extends HttpServlet {
    private BookingService bookingService = BookingServiceImpl.getBookingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            resp.sendRedirect("/travel_agency_war_exploded/login");
        } else {
            Integer roomId = Integer.parseInt(req.getParameter("id"));
            String startDateStr = req.getParameter("startDate");
            String endDateStr = req.getParameter("endDate");
            java.util.Date startDateUtil = null;
            java.util.Date endDateUtil = null;

            try {
                startDateUtil = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse(startDateStr);
                endDateUtil = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            LocalDate start = startDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = endDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                Date reserveDate = Date.valueOf(date);
                Booking booking = new Booking(reserveDate, roomId, user_id);
                bookingService.create(booking);
            }

            resp.sendRedirect("/travel_agency_war_exploded/result");
        }
    }
}
