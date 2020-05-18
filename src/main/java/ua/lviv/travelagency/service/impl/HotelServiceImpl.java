package ua.lviv.travelagency.service.impl;

import ua.lviv.travelagency.dao.HotelDao;
import ua.lviv.travelagency.dao.impl.HotelDaoImpl;
import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.service.HotelService;

import java.sql.Date;
import java.util.List;

public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;
    private static HotelService hotelServiceImpl;

    public static HotelService getHotelServiceImpl() {
        if (hotelServiceImpl == null)
            hotelServiceImpl = new HotelServiceImpl();
        return hotelServiceImpl;
    }

    private HotelServiceImpl() {
        hotelDao = new HotelDaoImpl();
    }

    @Override
    public Hotel create(Hotel hotel) {
        return hotelDao.create(hotel);
    }

    @Override
    public Hotel read(Integer id) {
        return hotelDao.read(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return hotelDao.update(hotel);
    }

    @Override
    public void delete(Integer id) {
        hotelDao.delete(id);
    }

    @Override
    public List<Hotel> readAll() {
        return hotelDao.readAll();
    }

    @Override
    public List<Hotel> readByHotelAndDate(Integer hotelId, Date date) {
        return hotelDao.readByHotelAndDate(hotelId, date);
    }

    @Override
    public List<Hotel> readByCityAndDate(String city, Date date) {
        return hotelDao.readByCityAndDate(city, date);
    }
}
