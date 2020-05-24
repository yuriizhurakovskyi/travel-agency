package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.BookingDao;
import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    private static String CREATE = "insert into booking(`date`, `room_id`, `user_id`) values (?,?,?)";
    private static String READ_BY_ID = "select * from booking where id = ?";
    private static String READ_BY_USER = "select * from booking where user_id = ?";
    private static String READ_BY_ROOM = "select * from booking where room_id = ?";
    private static String READ_ALL = "select * from booking";
    private static String DELETE_BY_ID = "delete from booking where id = ?";
    private static String UPDATE = "update booking SET date=?, room_id=?, user_id=? WHERE id=?";
    private static Logger LOGGER = LogManager.getLogger(BookingDaoImpl.class);

    @Override
    public Booking create(Booking booking) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(CREATE,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, new Date(booking.getDate().getTime()));
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setInt(3, booking.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                booking.setId(rs.getInt(1));
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return booking;
    }

    @Override
    public Booking read(Integer id) {
        Booking booking = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                result.next();
                Integer bookingId = result.getInt("id");
                Date date = result.getDate("date");
                Integer roomId = result.getInt("room_id");
                Integer userId = result.getInt("user_id");
                booking = new Booking(bookingId, date, roomId, userId);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return booking;
    }


    @Override
    public List<Booking> readByRoom(Integer roomId) {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ROOM)) {
            preparedStatement.setInt(1, roomId);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer bookingId = result.getInt("id");
                    Date date = result.getDate("date");
                    Integer _roomId = result.getInt("room_id");
                    Integer userId = result.getInt("user_id");
                    bookings.add(new Booking(bookingId, date, _roomId, userId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bookings;
    }

    @Override
    public List<Booking> readByUser(Integer userId) {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_USER)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer bookingId = result.getInt("id");
                    Date date = result.getDate("date");
                    Integer roomId = result.getInt("room_id");
                    Integer _userId = result.getInt("user_id");
                    bookings.add(new Booking(bookingId, date, roomId, _userId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bookings;
    }

    @Override
    public void createBooking(List<Booking> bookings) {
        try {
            ConnectionManager.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(CREATE,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Booking booking : bookings) {
                preparedStatement.setDate(1, new Date(booking.getDate().getTime()));
                preparedStatement.setInt(2, booking.getRoomId());
                preparedStatement.setInt(3, booking.getUserId());

                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    rs.next();
                    booking.setId(rs.getInt(1));
                }

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            ConnectionManager.getConnection().commit();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Booking update(Booking booking) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setDate(1, new Date(booking.getDate().getTime()));
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setInt(3, booking.getUserId());
            preparedStatement.setInt(4, booking.getId());
            preparedStatement.executeUpdate();
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return booking;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Booking> readAll() {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer bookingId = result.getInt("id");
                    Date date = result.getDate("date");
                    Integer roomId = result.getInt("room_id");
                    Integer userId = result.getInt("user_id");
                    bookings.add(new Booking(bookingId, date, roomId, userId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bookings;
    }
}
