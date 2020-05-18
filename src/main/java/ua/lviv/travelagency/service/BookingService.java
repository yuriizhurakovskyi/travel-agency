package ua.lviv.travelagency.service;

import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.util.List;

public interface BookingService extends AbstractCRUD<Booking> {
    List<Booking> readByRoom(Integer roomId);

    List<Booking> readByUser(Integer userId);
}
