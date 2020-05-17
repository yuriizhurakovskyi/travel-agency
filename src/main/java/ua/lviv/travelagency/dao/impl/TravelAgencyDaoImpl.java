package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.TravelAgencyDao;
import ua.lviv.travelagency.domain.Hotel;
import ua.lviv.travelagency.domain.TravelAgency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TravelAgencyDaoImpl implements TravelAgencyDao {

    private static String CREATE = "insert into agency(`name`, `address`, `email`) values (?,?,?)";
    private static String READ_BY_ID = "select * from agency where id = ?";
    private static String READ_ALL = "select * from agency";
    private static String UPDATE_BY_ID = "UPDATE agency SET name=?, address=?, email=? WHERE id=?";
    private static String DELETE_BY_ID = "delete from agency where id = ?";
    private static Logger LOGGER = LogManager.getLogger(TravelAgencyDaoImpl.class);

    @Override
    public TravelAgency create(TravelAgency travelAgency) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, travelAgency.getName());
            preparedStatement.setString(2, travelAgency.getAddress());
            preparedStatement.setString(3, travelAgency.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                travelAgency.setId(rs.getInt(1));
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return travelAgency;
    }

    @Override
    public TravelAgency read(Integer id) {
        TravelAgency travelAgency = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer agencyId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");

            travelAgency = new TravelAgency(agencyId, name, address, email);
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return travelAgency;
    }

    @Override
    public TravelAgency update(TravelAgency travelAgency) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)){
            preparedStatement.setString(1, travelAgency.getName());
            preparedStatement.setString(2, travelAgency.getAddress());
            preparedStatement.setString(3, travelAgency.getEmail());
            preparedStatement.setInt(4, travelAgency.getId());
            preparedStatement.executeUpdate();
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return travelAgency;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<TravelAgency> readAll() {
        List<TravelAgency> travelAgencies = new ArrayList<>();
        try ( PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer idagency = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    travelAgencies.add(new TravelAgency(idagency, name, address, email));

                }
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return travelAgencies;
    }
}
