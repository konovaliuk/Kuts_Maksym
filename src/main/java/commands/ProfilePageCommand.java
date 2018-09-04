package commands;

import dao.DataBaseFactory;
import dao.TicketDAO;
import enteties.Ticket;
import enteties.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProfilePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TicketDAO ticketDAO = DataBaseFactory.getTicketDAO();
        User user = (User) request.getSession().getAttribute("user");
        List<Ticket> tickets = ticketDAO.findTicketsByUserId(user.getId());
        request.setAttribute("tickets",tickets);

        return "profile.jsp";
    }
}
