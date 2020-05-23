package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.RoomDao;
import ua.lviv.travelagency.domain.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    private static String CREATE = "insert into room(`capacity`, `type`, `wifi`, `breakfast`, `price`, `hotel_id`) values (?, ?, ?, ?, ?, ?)";
    private static String READ_BY_ID = "select * from room where id=?";
    private static String READ_ALL = "select * from room";
    private static String UPDATE_BY_ID = "update room set capacity=?, type=?, wifi=?, breakfast=?, price=?, hotel_id=?  where id = ?";
    private static String DELETE_BY_ID = "delete from room where id=?";
    private static String READ_ROOM_BY_HOTEL_ID = "select * from room " +
            "left join booking on booking.room_id = room.id and booking.date between ? and ? " +
            "where room.hotel_id = ? and booking.room_id is NULL and booking.date between ? and ? is NULL;";
    private static Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    @Override
    public Room create(Room room) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(CREATE,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setString(2, room.getType());
            preparedStatement.setBoolean(3, room.getBreakfastIncluded());
            preparedStatement.setBoolean(4, room.getWifiIncluded());
            preparedStatement.setDouble(5, room.getPrice());
            preparedStatement.setInt(6, room.getHotelId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                room.setId(rs.getInt(1));
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return room;
    }

    @Override
    public Room read(Integer id) {
        Room room = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                result.next();
                Integer roomId = result.getInt("id");
                Integer capacity = result.getInt("capacity");
                String type = result.getString("type");
                Boolean wifi = result.getBoolean("wifi");
                Boolean breakfast = result.getBoolean("breakfast");
                Double price = result.getDouble("price");
                Integer hotelId = result.getInt("hotel_id");
                room = new Room(roomId, capacity, type, wifi, breakfast, price, hotelId);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return room;
    }

    @Override
    public Room update(Room room) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setString(2, room.getType());
            preparedStatement.setBoolean(3, room.getBreakfastIncluded());
            preparedStatement.setBoolean(4, room.getWifiIncluded());
            preparedStatement.setDouble(5, room.getPrice());
            preparedStatement.setInt(6, room.getHotelId());
            preparedStatement.setInt(7, room.getHotelId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return room;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Room> readAll() {
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer roomId = result.getInt("id");
                    Integer capacity = result.getInt("capacity");
                    String type = result.getString("type");
                    Boolean wifi = result.getBoolean("wifi");
                    Boolean breakfast = result.getBoolean("breakfast");
                    Double price = result.getDouble("price");
                    Integer hotelId = result.getInt("hotel_id");
                    rooms.add(new Room(roomId, capacity, type, wifi, breakfast, price, hotelId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return rooms;
    }

    @Override
    public List<Room> readRoomsByHotelAndDates(Integer id, Date startDate, Date endDate) {
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOM_BY_HOTEL_ID)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setInt(3, id);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer roomId = result.getInt("id");
                    Integer capacity = result.getInt("capacity");
                    String type = result.getString("type");
                    Boolean wifi = result.getBoolean("wifi");
                    Boolean breakfast = result.getBoolean("breakfast");
                    Double price = result.getDouble("price");
                    Integer hotelId = result.getInt("hotel_id");
                    rooms.add(new Room(roomId, capacity, type, wifi, breakfast, price, hotelId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return rooms;
    }
}
