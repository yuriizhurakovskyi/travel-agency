package ua.lviv.travelagency.service;

import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface RoomService extends AbstractCRUD<Room> {
    List<Room> readRoomsByHotelAndDates(Integer id, Date startDate, Date endDate);

    Map<Integer, Integer> readRoomUsage(Integer hotelId);

    Map<String, Integer> readClientCount();
}
