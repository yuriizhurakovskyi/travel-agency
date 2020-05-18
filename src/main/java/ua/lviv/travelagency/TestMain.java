package ua.lviv.travelagency;

import ua.lviv.travelagency.dao.HotelDao;
import ua.lviv.travelagency.dao.impl.HotelDaoImpl;
import ua.lviv.travelagency.domain.Hotel;

import java.sql.Date;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        HotelDao hotelDao = new HotelDaoImpl();
        List<Hotel> hotel = hotelDao.readByCityAndDate("Lviv", new Date(20200517));
        hotel.forEach(System.out::println);
    }
}
