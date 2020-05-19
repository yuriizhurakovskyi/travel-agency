package ua.lviv.travelagency.dao;

import ua.lviv.travelagency.domain.User;
import ua.lviv.travelagency.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
