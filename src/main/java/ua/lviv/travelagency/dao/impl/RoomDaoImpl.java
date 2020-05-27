package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.RoomDao;
import ua.lviv.travelagency.domain.Room;
import ua.lviv.travelagency.servlet.SearchHotelByCityAndDateServlet;

import java.sql.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDaoImpl implements RoomDao {
    private static String CREATE = "insert into room(`capacity`, `type`, `wifi`, `breakfast`, `price`, `hotel_id`) values (?, ?, ?, ?, ?, ?)";
    private static String READ_BY_ID = "select * from room where id=?";
    private static String READ_ALL = "select * from room";
    private static String UPDATE_BY_ID = "update room set capacity=?, type=?, wifi=?, breakfast=?, price=?, hotel_id=?  where id = ?";
    private static String DELETE_BY_ID = "delete from room where id=?";
    private static String READ_ROOM_BY_HOTEL_ID = "select * from room " +
            "left join booking on booking.room_id = room.id and booking.date between ? and ? " +
            "where room.hotel_id = ? and booking.room_id is NULL and booking.date between ? and ? is NULL;";
    private static String READ_ROOM_USAGE = "select room.id, COUNT(room.id) as room_usage from room " +
            "left join booking on room.id = booking.room_id " +
            "where hotel_id = ? and booking.date between ? and ? " +
            "GROUP BY room.id;";
    private static String READ_USER_COUNT = "select hotel.name, count(distinct room.id) as usersCount from booking " +
            "inner join room on room.id = booking.room_id " +
            "inner join hotel on hotel.id = room.hotel_id " +
            "group by hotel.id";
    private static String READ_ROOMS_BY_HOTEL_ID = "select * from room where room.hotel_id=?";
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
    public List<Room> readRoomsByHotelId(Integer hotel_id) {
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOMS_BY_HOTEL_ID)) {
            preparedStatement.setInt(1, hotel_id);
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

    @Override
    public Map<Integer, Integer> readRoomUsage(Integer hotelId) {
        Map<Integer, Integer> usageMap = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = SearchHotelByCityAndDateServlet.dateInSql(sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date endDate = SearchHotelByCityAndDateServlet.dateInSql(sdf.format(cal.getTime()));
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOM_USAGE)) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setDate(2, startDate);
            preparedStatement.setDate(3, endDate);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer roomId = result.getInt("id");
                    Integer room_usage = result.getInt("room_usage");
                    usageMap.put(roomId, room_usage);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return usageMap;
    }

    @Override
    public Map<String, Integer> readClientCount() {
        Map<String, Integer> hotelBookedCount = new HashMap<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_USER_COUNT)) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    String hotelName = result.getString("name");
                    Integer userId = result.getInt("usersCount");
                    hotelBookedCount.put(hotelName, userId);

                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return hotelBookedCount;
    }
}
