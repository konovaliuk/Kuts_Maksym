package commands;

import com.google.gson.Gson;
import enteties.Ship;
import org.apache.log4j.Logger;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetShipListCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetShipListCommand.class.getSimpleName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Ship> ships = AdminPageService.getAllShips();
        String jsonString = new Gson().toJson(ships);
        try(PrintWriter out = response.getWriter()){
            out.print(jsonString);
        } catch (IOException e) {
           logger.error(e.getMessage());
        }
        return "ajax";
    }
}
