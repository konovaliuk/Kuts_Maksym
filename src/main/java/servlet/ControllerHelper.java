package servlet;



import commands.*;

import java.util.HashMap;

public class ControllerHelper {
    private static ControllerHelper instance;
    private HashMap<String,Command> commands = new HashMap<>();

    private ControllerHelper(){
        commands.put("changeLanguage",new ChangeLanguageCommand());
        commands.put("register",new RegisterCommand());
        commands.put("login",new LoginCommand());
        commands.put("logout",new LogoutCommand());
        commands.put("shipPage",new ShipPageCommand());
        commands.put("getShipList",new GetShipListCommand());
        commands.put("adminPageCommand",new AdminPageCommand());
        commands.put("ticketTypeHasServiceCommand",new TicketTypeHasServicesCommand());
        commands.put("updateTypeHasService",new UpdateTypeHasServicesCommand());
        commands.put("buyTicketCommand",new BuyTicketCommand());
    }

    public static ControllerHelper getInstance(){
        if(instance == null){
            instance = new ControllerHelper();
        }
        return instance;
    }

    public Command getCommand(String command){
        Command result = commands.get(command);
        if (result == null){
            result = new MissCommand();
        }

        return result;
    }
}
