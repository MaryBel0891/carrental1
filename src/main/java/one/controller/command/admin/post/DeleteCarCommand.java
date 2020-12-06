package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class DeleteCarCommand implements Command {
    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId"));
        try {
            carService.deleteCar(carId);
        } catch (Exception e) {

        }
        return "redirect:/admin/adminCarList";
    }
}
