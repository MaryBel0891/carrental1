package one.model.service;


import one.model.dao.CarDao;
import one.model.dao.DaoFactory;
import one.model.entity.Car;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Default
public class CarService {
    DaoFactory factory;
    CarDao dao;

    public CarService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createCarDao();
    }
    public void addCar(Car car){
        dao.create(car);
    }

    public void deleteCar(int id){
        dao.delete(id);
    }

    public List<Car> getCars(){
        return dao.findAll();
    }

    public Car getCar(int carId) throws Exception {
        return dao.findById(carId).orElseThrow(Exception::new);
    }

    public void updateCar(Car car){
        dao.update(car);
    }

    public List<Car> searchCarsFromList(List<Car> cars, String carName, String price, String insuranceValue){
        List<Car> carsResult = cars;
        if (carName != null && !carName.isEmpty()){
            carsResult = carsResult.stream().filter(a -> a.getName().equals(carName)).collect(Collectors.toList());
        }
        if (price != null && !price.isEmpty()){
            carsResult = carsResult.stream().filter(a -> a.getPrice() == Integer.parseInt(price)).collect(Collectors.toList());
        }
        if (insuranceValue != null && !insuranceValue.isEmpty()){
            carsResult = carsResult.stream().filter(a -> a.getInsuranceValue() == Integer.parseInt(insuranceValue)).collect(Collectors.toList());
        }

        return carsResult;
    }
}
