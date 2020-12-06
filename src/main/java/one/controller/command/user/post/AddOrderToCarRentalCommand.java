package one.controller.command.user.post;


import one.controller.command.Command;
import one.model.entity.CarRental;
import one.model.service.CarRentalService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddOrderToCarRentalCommand implements Command {
    @Inject
    CarRentalService carRentalService;


    @Override
    public String execute(HttpServletRequest request) {

        int carId = Integer.parseInt(request.getParameter("carId"));
        String name = request.getParameter("name");
        int dayAmount = Integer.parseInt(request.getParameter("dayAmount"));
        int price = Integer.parseInt(request.getParameter("price"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        CarRental carRental = new CarRental.Builder()
                .carId(carId)
                .clientId((Integer) request.getSession().getAttribute("userId"))
                .daysAmount(dayAmount)
                .price(price*dayAmount)
                .startDate(date)
                .build();
        carRentalService.saveNewCarRental(carRental);
        request.setAttribute("carRental", carRental);
        request.setAttribute("carName", name);
        request.setAttribute("carPrice", price);
        return "/WEB-INF/user/order_submit.jsp";
    }
}
