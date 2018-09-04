package commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enteties.User;
import org.apache.log4j.Logger;
import service.ShipPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

public class BuyTicketCommand implements Command {
    private static final Logger logger = Logger.getLogger(BuyTicketCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String responseMessage;

            User user = (User) request.getSession().getAttribute("user");
            Long shipId = Long.valueOf(request.getParameter("shipId"));
            Integer ticketTypeId = Integer.valueOf(request.getParameter("ticketTypeId"));
            int count = Integer.parseInt(request.getParameter("count"));
            Type type = new TypeToken<List<Long>>(){}.getType();
            List<Long> excursionList = new Gson().fromJson(request.getParameter("excursionArray"), type);

            responseMessage = ShipPageService.BuyTickets(shipId,ticketTypeId,count,excursionList,user.getId());


        try (PrintWriter out = response.getWriter()) {
            out.print(responseMessage);
        } catch (IOException e) {
            logger.error(e.getMessage());
            response.setStatus(500);
            return "ajax";
        }

        return "ajax";
    }
}
