package commands;

import enteties.AdditionalService;
import enteties.User;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.getUserRole().equals("admin")){
            return "index.jsp";
        }
        request.setAttribute("ships", AdminPageService.getAllShips());
        request.setAttribute("ticketTypes",AdminPageService.getAllTicketTypes());
        request.setAttribute("services", AdminPageService.getAllServices());


        return "adminpanel.jsp";
    }
}
