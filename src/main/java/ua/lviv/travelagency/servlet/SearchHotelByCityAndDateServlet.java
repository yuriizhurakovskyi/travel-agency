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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/hotelByCityAndDate")
public class SearchHotelByCityAndDateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
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

        HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();
        List<Hotel> hotels = hotelService.readByCityAndDate(city.trim(), startDateSql, endDateSql);

        req.setAttribute("startDate", startDateStr);
        req.setAttribute("endDate", endDateStr);
        req.setAttribute("hotels", hotels);

        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
