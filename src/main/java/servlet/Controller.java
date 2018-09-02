package servlet;

import commands.Command;
import dao.impl.TicketDAOImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private final static Logger logger = Logger.getLogger(Controller.class.getSimpleName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        performTask(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        performTask(req, resp);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) {
        Command command = ControllerHelper.getInstance().getCommand(request.getParameter("command"));
        String path = command.execute(request, response);

        if(request.getAttribute("command") != null){
            path = ControllerHelper.getInstance().getCommand((String) request.getAttribute("command")).execute(request,response);
        }
        String filterPath = (String) request.getAttribute("redirect");

        if (filterPath == null) {
            switch (path) {
                case "index.jsp":
                    break;
                case "/":
                    break;
                case "ajax":
                    break;
                default:
                    path = "WEB-INF/" + path;
                    break;
            }
        } else {
            switch (filterPath) {
                case "index.jsp":
                    path = filterPath;
                    break;
                default:
                    path = "WEB-INF/" + filterPath;
                    break;
            }
        }

        try {
            if(path.equals("index.jsp")){
                response.sendRedirect(path);
            }else {
                request.getRequestDispatcher(path).forward(request,response);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }

    }

}
