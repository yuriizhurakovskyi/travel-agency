package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.HotelDao;
import ua.lviv.travelagency.domain.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {

    private static String CREATE = "insert into hotel(`name`, `rating`, `country`, `city`,`room_count`, `agency_id`) values (?,?,?,?,?,?)";
    private static String READ_BY_ID = "select * from hotel where id = ?";
    private static String READ_ALL = "select * from hotel";
    private static String UPDATE_BY_ID = "update hotel set name=?, rating=?, country=?, city=?, room_count=?, agency_id=?  where id = ?";
    private static String DELETE_BY_ID = "delete from hotel where id = ?";
    private static String READ_HOTEL_BY_DATE = "select distinct hotel.id, hotel.name, hotel.rating, hotel.country, hotel.city, " +
            "hotel.room_count, hotel.agency_id from hotel " +
            "inner join room on room.hotel_id = hotel.id " +
            "left join booking on booking.room_id = room.id and booking.date = ?" +
            "where hotel.id = ? and booking.room_id is NULL";
    private static String READ_BY_CITY_AND_DATE = "select distinct hotel.id, hotel.name, hotel.rating, hotel.country, hotel.city, hotel.room_count, hotel.agency_id from hotel " +
            "inner join room on room.hotel_id = hotel.id " +
            "left join booking on booking.room_id = room.id and booking.date between ? and ? " +
            "where hotel.city = ? and booking.room_id is NULL and booking.date between ? and ?  is NULL;";
    private static String READ_ALL_CITIES_AND_COUNTRIES = "select distinct hotel.country, hotel.city from hotel";
    private static String READ_BY_COUNTRY_AND_CITY = "select * from hotel where hotel.country=? and hotel.city=?";
    private static Logger LOGGER = LogManager.getLogger(HotelDaoImpl.class);

    @Override
    public Hotel create(Hotel hotel) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setInt(2, hotel.getRating());
            preparedStatement.setString(3, hotel.getCountry());
            preparedStatement.setString(4, hotel.getCity());
            preparedStatement.setInt(5, hotel.getRoom_count());
            preparedStatement.setInt(6, hotel.getAgencyId());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                hotel.setId(rs.getInt(1));
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return hotel;
    }

    @Override
    public Hotel read(Integer id) {
        Hotel hotel = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                Integer hotelId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer rating = resultSet.getInt("rating");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                Integer roomCount = resultSet.getInt("room_count");
                Integer agencyId = resultSet.getInt("agency_id");
                hotel = new Hotel(hotelId, name, rating, country, city, roomCount, agencyId);
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setInt(2, hotel.getRating());
            preparedStatement.setString(3, hotel.getCountry());
            preparedStatement.setString(4, hotel.getCity());
            preparedStatement.setInt(5, hotel.getRoom_count());
            preparedStatement.setInt(6, hotel.getAgencyId());
            preparedStatement.setInt(7, hotel.getId());
            preparedStatement.executeUpdate();
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return hotel;
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
    public List<Hotel> readAll() {
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer hotelId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer rating = resultSet.getInt("rating");
                    String country = resultSet.getString("country");
                    String city = resultSet.getString("city");
                    Integer roomCount = resultSet.getInt("room_count");
                    Integer agencyId = resultSet.getInt("agency_id");
                    hotels.add(new Hotel(hotelId, name, rating, country, city, roomCount, agencyId));
                }
                ConnectionManager.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }


        return hotels;
    }

    @Override
    public List<Hotel> readByHotelAndDate(Integer hotelId, Date date) {
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_HOTEL_BY_DATE)) {
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, hotelId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer hotel_Id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer rating = resultSet.getInt("rating");
                    String country = resultSet.getString("country");
                    String city = resultSet.getString("city");
                    Integer roomCount = resultSet.getInt("room_count");
                    Integer agencyId = resultSet.getInt("agency_id");
                    hotels.add(new Hotel(hotel_Id, name, rating, country, city, roomCount, agencyId));
                }
                ConnectionManager.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return hotels;
    }

    @Override
    public List<Hotel> readByCityAndDate(String city, Date startDate, Date endDate) {
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_CITY_AND_DATE)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setString(3, city);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer hotel_Id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer rating = resultSet.getInt("rating");
                    String country = resultSet.getString("country");
                    String cityName = resultSet.getString("city");
                    Integer roomCount = resultSet.getInt("room_count");
                    Integer agencyId = resultSet.getInt("agency_id");
                    hotels.add(new Hotel(hotel_Id, name, rating, country, cityName, roomCount, agencyId));
                }
                ConnectionManager.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return hotels;
    }

    @Override
    public List<String> readAllCitiesAndCountries() {
        List<String> countriesAndCities = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL_CITIES_AND_COUNTRIES)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String country = resultSet.getString("country");
                    String city = resultSet.getString("city");
                    countriesAndCities.add(city + ", " + country);
                }
                ConnectionManager.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return countriesAndCities;
    }

    @Override
    public List<Hotel> readByCountryAndCity(String country, String city) {
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_COUNTRY_AND_CITY)) {
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer hotelId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer rating = resultSet.getInt("rating");
                    String _country = resultSet.getString("country");
                    String _city = resultSet.getString("city");
                    Integer roomCount = resultSet.getInt("room_count");
                    Integer agencyId = resultSet.getInt("agency_id");
                    hotels.add(new Hotel(hotelId, name, rating, _country, _city, roomCount, agencyId));
                }
                ConnectionManager.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return hotels;
    }
}
