package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UpdateCarCommand implements Command {
    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId2"));
        try {
           request.setAttribute("car", carService.getCar(carId));
        } catch (Exception e) {
        }
        return "/WEB-INF/admin/update_car.jsp";
    }
}