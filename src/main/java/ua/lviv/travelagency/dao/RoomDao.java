package ua.lviv.travelagency.dao;

import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.shared.AbstractCRUD;

import java.sql.Date;
import java.util.List;

public interface RoomDao extends AbstractCRUD<Room> {
    List<Room> readRoomsByHotelAndDates(Integer id, Date startDate, Date endDate);
}
