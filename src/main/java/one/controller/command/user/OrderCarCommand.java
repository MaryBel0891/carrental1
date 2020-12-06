package one.controller.command.user;




import one.controller.command.Command;
import one.model.service.CarService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class OrderCarCommand implements Command {
    @Inject
    CarService carService;


    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("items", carService.getCars());
        return "/WEB-INF/user/carList.jsp";
    }
}
