package one.controller;


import one.controller.command.Command;
import one.controller.command.LogoutCommand;
import one.controller.command.admin.AddNewCarCommand;
import one.controller.command.admin.AdminCarListCommand;
import one.controller.command.admin.post.AddNewCarPostCommand;
import one.controller.command.admin.post.DeleteCarCommand;
import one.controller.command.admin.post.UpdateCarCommand;
import one.controller.command.admin.post.UpdateCarSubmitCommand;
import one.controller.command.guest.LoginPageCommand;
import one.controller.command.guest.MainCommand;
import one.controller.command.guest.RegistrationPageCommand;
import one.controller.command.guest.post.LoginCommand;
import one.controller.command.guest.post.RegistrationCommand;
import one.controller.command.user.OrderCarCommand;
import one.controller.command.user.post.AddOrderToCarRentalCommand;
import one.controller.command.user.post.CreateOrderCommand;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet("/")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();


    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        commands.put("logout",container.select(LogoutCommand.class).get());
        commands.put("login",container.select(LoginPageCommand.class).get());
        commands.put("log", container.select(LoginCommand.class).get());
        commands.put("reg", container.select(RegistrationCommand.class).get());
        commands.put("registration",container.select(RegistrationPageCommand.class).get());
        commands.put("",container.select(MainCommand.class).get());

        commands.put("admin/add_new_car",container.select(AddNewCarCommand.class).get());
        commands.put("admin/adminCarList", container.select(AdminCarListCommand.class).get());
        commands.put("admin/deleteCar", container.select(DeleteCarCommand.class).get());
        commands.put("admin/updateCar", container.select(UpdateCarCommand.class).get());
        commands.put("admin/updateCarSubmit",container.select(UpdateCarSubmitCommand.class).get());
        commands.put("admin/addNewCarPost", container.select(AddNewCarPostCommand.class).get());

        commands.put("user/order_car", container.select(OrderCarCommand.class).get());
        commands.put("user/order_create", container.select(CreateOrderCommand.class).get());
        commands.put("user/addOrderToCarRental", container.select(AddOrderToCarRentalCommand.class).get());

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/web/" , "");
        Command command = commands.getOrDefault(path ,
                (req)->"/error.jsp");
        String page = command.execute(request);
        if (page.contains("redirect"))
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
        else
            request.getRequestDispatcher(page).forward(request,response);
    }

}
