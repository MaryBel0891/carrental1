package one.controller.command.user.post;


import one.controller.command.Command;
import one.model.service.CarRentalService;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    @Inject
    CarRentalService carRentalService;
    @Inject
    CarService carService;



    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId"));
        try {
            request.setAttribute("car", carService.getCar(carId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/user/create_order.jsp";
    }
}
