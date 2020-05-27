package ua.lviv.travelagency.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lviv.travelagency.connection.ConnectionManager;
import ua.lviv.travelagency.dao.UserDao;
import ua.lviv.travelagency.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static String CREATE = "insert into user(`first_name`, `second_name`, `user_role`, `phone_number`, `email`, `password`) values (?, ?, ?, ?, ?, ?)";
    private static String READ_BY_ID = "select * from user where id=?";
    private static String READ_ALL = "select * from user";
    private static String READ_USER_BY_EMAIL = "select * from user where email = ?";
    private static String UPDATE_BY_ID = "update user set first_name=?, second_name=?, user_role=?, phone_number=?, email=?, password=?  where id = ?";
    private static String DELETE_BY_ID = "delete from user where id=?";
    private static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(CREATE,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                user.setId(rs.getInt(1));
            }
            ConnectionManager.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            Integer userId = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("second_name");
            String role = result.getString("user_role");
            String phone = result.getString("phone_number");
            String email = result.getString("email");
            String password = result.getString("password");
            user = new User(userId, firstName, lastName, email, phone, role, password);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(4, user.getPhoneNumber());

            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
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
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer userId = result.getInt("id");
                    String firstName = result.getString("first_name");
                    String lastName = result.getString("second_name");
                    String role = result.getString("user_role");
                    String phone = result.getString("phone_number");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    users.add(new User(userId, firstName, lastName, email, phone, role, password));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            Integer userId = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("second_name");
            String role = result.getString("user_role");
            String phone = result.getString("phone_number");
            String email_user = result.getString("email");
            String password = result.getString("password");
            user = new User(userId, firstName, lastName, email_user, phone, role, password);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }
}
