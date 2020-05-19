package ua.lviv.travelagency.service;

import ua.lviv.travelagency.domain.User;
import ua.lviv.travelagency.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
