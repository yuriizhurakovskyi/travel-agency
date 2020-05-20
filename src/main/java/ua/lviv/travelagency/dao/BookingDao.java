package ua.lviv.travelagency.dao;

import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.util.List;

public interface BookingDao extends AbstractCRUD<Booking> {
    List<Booking> readByRoom(Integer roomId);

    List<Booking> readByUser(Integer userId);
}
