package one.controller.command.admin;


import one.controller.command.Command;
import one.model.entity.Car;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCarListCommand  implements Command {
    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        String carName = request.getParameter("carName");
        String carPrice = request.getParameter("price");
        String carInsuranceValue= request.getParameter("insuranceValue");
        List<Car> cars =  carService.searchCarsFromList(carService.getCars(), carName, carPrice, carInsuranceValue);
        request.setAttribute("items", cars);

        return "/WEB-INF/admin/adminCarList.jsp";
    }
}
