package one.model.service;


import one.model.dao.CarRentalDao;
import one.model.dao.DaoFactory;
import one.model.entity.CarRental;

import javax.enterprise.inject.Default;

@Default
public class CarRentalService {
    DaoFactory factory;
    CarRentalDao dao;

    public CarRentalService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createCarRentalDao();
    }

    public void saveNewCarRental(CarRental carRental){
        dao.create(carRental);
    }

    public void update(CarRental carRental){
        dao.update(carRental);}


    public CarRental getCarRentalById(int id) throws Exception {
        return dao.findById(id).orElseThrow(Exception::new);
    }


}
