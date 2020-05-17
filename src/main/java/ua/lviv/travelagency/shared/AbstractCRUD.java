package ua.lviv.travelagency.shared;

import java.util.List;

public interface AbstractCRUD<T> {
    T create(T t);

    T read(Integer id);

    T update(T t);

    void delete(Integer id);

    List<T> readAll();
}