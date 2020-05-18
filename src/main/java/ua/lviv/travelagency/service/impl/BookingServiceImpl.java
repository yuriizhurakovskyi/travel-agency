package ua.lviv.travelagency.service.impl;

import ua.lviv.travelagency.dao.BookingDao;
import ua.lviv.travelagency.dao.impl.BookingDaoImpl;
import ua.lviv.travelagency.domain.Booking;
import ua.lviv.travelagency.service.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao;
    private static BookingService bookingServiceImpl;

    public static BookingService getBookingServiceImpl() {
        if(bookingServiceImpl == null)
            bookingServiceImpl = new BookingServiceImpl();
        return bookingServiceImpl;
    }

    private BookingServiceImpl() {
        bookingDao = new BookingDaoImpl();
    }

    @Override
    public Booking create(Booking booking) {
        return bookingDao.create(booking);
    }

    @Override
    public Booking read(Integer id) {
        return bookingDao.read(id);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingDao.update(booking);
    }

    @Override
    public void delete(Integer id) {
        bookingDao.delete(id);
    }

    @Override
    public List<Booking> readAll() {
        return bookingDao.readAll();
    }

    @Override
    public List<Booking> readByRoom(Integer roomId) {
        return bookingDao.readByRoom(roomId);
    }

    @Override
    public List<Booking> readByUser(Integer userId) {
        return bookingDao.readByUser(userId);
    }
}
