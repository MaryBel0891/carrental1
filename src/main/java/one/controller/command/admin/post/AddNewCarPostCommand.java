package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.entity.Car;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class AddNewCarPostCommand implements Command {

    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int insuranceValue = Integer.parseInt(request.getParameter("insuranceValue"));
            try {
                carService.addCar(new Car(name, price, insuranceValue));
            } catch (Exception e) {
                System.out.println(e);
            }
        return "redirect:/admin/add_new_car";
    }
}
