package ua.lviv.travelagency.service;

import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.sql.Date;
import java.util.List;

public interface HotelService extends AbstractCRUD<Hotel> {
    List<Hotel> readByHotelAndDate(Integer hotelId, Date date);

    List<Hotel> readByCityAndDate(String city, Date startDate, Date endDate);

    List<String> readAllCitiesAndCountries();

    List<Hotel> readByCountryAndCity(String country, String city);
}
