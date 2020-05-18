package ua.lviv.travelagency.service.impl;

import ua.lviv.travelagency.dao.TravelAgencyDao;
import ua.lviv.travelagency.dao.impl.TravelAgencyDaoImpl;
import ua.lviv.travelagency.domain.TravelAgency;
import ua.lviv.travelagency.service.TravelAgencyService;

import java.util.List;

public class TravelAgencyServiceImpl implements TravelAgencyService {

    private TravelAgencyDao travelAgencyDao;
    private static TravelAgencyService travelAgencyServiceImpl;

    public static TravelAgencyService getTravelAgencyServiceImpl() {
        if (travelAgencyServiceImpl == null)
            travelAgencyServiceImpl = new TravelAgencyServiceImpl();
        return travelAgencyServiceImpl;
    }

    private TravelAgencyServiceImpl() {
        travelAgencyDao = new TravelAgencyDaoImpl();
    }

    @Override
    public TravelAgency create(TravelAgency travelAgency) {
        return travelAgencyDao.create(travelAgency);
    }

    @Override
    public TravelAgency read(Integer id) {
        return travelAgencyDao.read(id);
    }

    @Override
    public TravelAgency update(TravelAgency travelAgency) {
        return travelAgencyDao.update(travelAgency);
    }

    @Override
    public void delete(Integer id) {
        travelAgencyDao.delete(id);
    }

    @Override
    public List<TravelAgency> readAll() {
        return travelAgencyDao.readAll();
    }
}
