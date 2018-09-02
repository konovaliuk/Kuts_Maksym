package commands;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class TicketTypeHasServicesCommand implements Command {
    private final static Logger logger = Logger.getLogger(TicketTypeHasServicesCommand.class.getSimpleName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long ship_id = Long.valueOf(request.getParameter("ship_id"));
        HashMap<Integer, List<Integer>> result = AdminPageService.getTypeServiceDependencyDyShipId(ship_id);
        String resToJson = new Gson().toJson(result);
        try(PrintWriter out = response.getWriter()){
            out.print(resToJson);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "ajax";
    }
}
