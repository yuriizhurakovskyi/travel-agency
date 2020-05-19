package ua.lviv.travelagency.service.impl;

import ua.lviv.travelagency.dao.UserDao;
import ua.lviv.travelagency.dao.impl.UserDaoImpl;
import ua.lviv.travelagency.domain.User;
import ua.lviv.travelagency.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private static UserService userServiceImpl;

    public static UserService getUserServiceImpl() {
        if (userServiceImpl == null)
            userServiceImpl = new UserServiceImpl();
        return userServiceImpl;
    }

    private UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
