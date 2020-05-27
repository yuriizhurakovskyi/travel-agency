package ua.lviv.travelagency.dao;

import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface BookingDao extends AbstractCRUD<Booking> {
    List<Booking> readByRoom(Integer roomId);

    List<Booking> readByUser(Integer userId);

    void createBooking(List<Booking> bookings);

    Map<String, List<Integer>> readAllBookedRoomCountFromHotels();
}
