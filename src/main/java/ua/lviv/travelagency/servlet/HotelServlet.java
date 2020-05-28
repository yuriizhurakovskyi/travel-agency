package ua.lviv.travelagency.servlet;

import com.google.gson.Gson;
import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.service.HotelService;
import ua.lviv.travelagency.service.impl.HotelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hotels")
public class HotelServlet extends HttpServlet {
    private HotelService hotelService = HotelServiceImpl.getHotelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> citiesAndCountries = hotelService.readAllCitiesAndCountries();
        citiesAndCountries.sort(String::compareTo);
        req.setAttribute("citiesAndCountries", citiesAndCountries);
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            req.setAttribute("userLogged", "no");
        } else req.setAttribute("userLogged", "yes");
        req.getRequestDispatcher("WEB-INF/hotels.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedCity = req.getParameter("selectedCity");
        String[] strings = selectedCity.split(", ");
        List<Hotel> hotels = hotelService.readByCountryAndCity(strings[1].trim(), strings[0].trim());
        String json = new Gson().toJson(hotels);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
