package ua.lviv.travelagency.dao;

import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.sql.Date;
import java.util.List;

public interface HotelDao extends AbstractCRUD<Hotel> {
    List<Hotel> readByHotelAndDate(Integer hotelId, Date date);

    List<Hotel> readByCityAndDate(String city, Date startDate, Date endDate);
}
