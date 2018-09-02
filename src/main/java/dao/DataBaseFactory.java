package dao;

import dao.impl.*;

public class DataBaseFactory {
    public static UserDAOImpl getUserDAO(){
        return UserDAOImpl.getInstance();
    }
    public static ShipDAOImpl getShipDAO(){return ShipDAOImpl.getInstance();}
    public static AdditionalServiceDAOImpl getServiceDAO(){return AdditionalServiceDAOImpl.getInstance();}
    public static TicketTypeDAOImpl getTicketTypeDAO(){
        return TicketTypeDAOImpl.getInstance();
    }
    public static TicketTypeHasAdditionalServiceAndShipDAOImpl getTypeServiceDAO(){
        return TicketTypeHasAdditionalServiceAndShipDAOImpl.getInstance();
    }
    public static TicketDAOImpl getTicketDAO(){return TicketDAOImpl.getInstance();}
    public static RouteHasPortDAOImpl getRouteHasPortDAO(){
        return RouteHasPortDAOImpl.getInstance();
    }
    public static PortDAOImpl getPortDAO(){
        return PortDAOImpl.getInstance();
    }
    public static ExcursionDAOImpl getExcursionDAO(){
        return ExcursionDAOImpl.getInstance();
    }

}
