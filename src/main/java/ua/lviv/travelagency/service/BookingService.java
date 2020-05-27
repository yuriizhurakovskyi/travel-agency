package ua.lviv.travelagency.service;

import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.util.List;
import java.util.Map;

public interface BookingService extends AbstractCRUD<Booking> {
    List<Booking> readByRoom(Integer roomId);

    List<Booking> readByUser(Integer userId);

    void createBooking(List<Booking> bookings);

    Map<String, Integer> readAverageReserveTime();

}
