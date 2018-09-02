package commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class UpdateTypeHasServicesCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateTypeHasServicesCommand.class.getSimpleName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String json = request.getParameter("json");
        Long ship_id = Long.valueOf(request.getParameter("ship_id"));
        Gson gson = new Gson();

        Type type = new TypeToken<HashMap<Integer,List<String>>>(){}.getType();
        HashMap<Integer, List<Integer>> toUpdate = gson.fromJson(json,type);

        AdminPageService.updateTypeServiceDependency(toUpdate,ship_id);
        try(PrintWriter out = response.getWriter()) {
            out.print("Success");
        } catch (IOException e) {
          logger.error(e.getMessage());
        }
        return "ajax";
    }
}
