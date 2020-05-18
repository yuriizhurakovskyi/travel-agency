package ua.lviv.travelagency.service.impl;

import ua.lviv.travelagency.dao.RoomDao;
import ua.lviv.travelagency.dao.impl.RoomDaoImpl;
import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao;
    private static RoomService roomServiceImpl;

    public static RoomService getRoomServiceImpl() {
        if (roomServiceImpl == null)
            roomServiceImpl = new RoomServiceImpl();
        return roomServiceImpl;
    }

    private RoomServiceImpl() {
        roomDao = new RoomDaoImpl();
    }

    @Override
    public Room create(Room room) {
        return roomDao.create(room);
    }

    @Override
    public Room read(Integer id) {
        return roomDao.read(id);
    }

    @Override
    public Room update(Room room) {
        return roomDao.update(room);
    }

    @Override
    public void delete(Integer id) {
        roomDao.delete(id);
    }

    @Override
    public List<Room> readAll() {
        return roomDao.readAll();
    }
}
