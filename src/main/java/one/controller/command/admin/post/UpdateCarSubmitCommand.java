package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.entity.Car;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UpdateCarSubmitCommand implements Command {
    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("id"));
        String carName = request.getParameter("name");
        int carPrice = Integer.parseInt(request.getParameter("price"));
        int carInsuranceValue = Integer.parseInt(request.getParameter("insuranceValue"));
        Car car = new Car(carId, carName, carPrice, carInsuranceValue);
        carService.updateCar(car);
        return "redirect:/admin/adminCarList";
    }
}
