package one.controller.command.admin;


import one.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AddNewCarCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/add_new_car.jsp";
    }
}
