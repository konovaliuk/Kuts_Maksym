package tag;

import dao.*;
import enteties.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TicketTag implements Tag {
    private Long ticketId;
    private PageContext pageContext;

    public void setTicketId(Long ticketId){
        this.ticketId = ticketId;
    }
    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public int doStartTag() throws JspException {
        TicketDAO ticketDAO = DataBaseFactory.getTicketDAO();
        TicketTypeDAO typeDAO = DataBaseFactory.getTicketTypeDAO();
        ShipDAO shipDAO = DataBaseFactory.getShipDAO();
        TicketTypeHasAdditionalServiceAndShipDAO dao = DataBaseFactory.getTypeServiceDAO();
        AdditionalServiceDAO serviceDAO = DataBaseFactory.getServiceDAO();
        TicketHasExcursionDAO ticketHasExcursionDAO = DataBaseFactory.getTicketHasExcursionDAO();
        ExcursionDAO excursionDAO = DataBaseFactory.getExcursionDAO();

        Ticket ticket = ticketDAO.findTicketById(ticketId);

        TicketType ticketType = typeDAO.findTicketTypeById(ticket.getTicketTypeId());
        Ship ship = shipDAO.findShipById(ticket.getShipId());



        HashMap<Integer,List<Integer>> typeServicesDependency = dao.findListOfTicketTypesAndServicesByShipId(ship.getId());
        List<AdditionalService> additionalServices = new ArrayList<>();


        for (Integer serviceId:typeServicesDependency.get(ticketType.getId())){
            additionalServices.add(serviceDAO.findServiceById(serviceId));
        }


        List<Long> excursionsId = ticketHasExcursionDAO.findExcursionsIdByTicketId(ticket.getId());
        List<Excursion> excursionList = new ArrayList<>();
        for(Long excursionId : excursionsId){
            excursionList.add(excursionDAO.findExcursionById(excursionId));
        }


        String startDate = String.valueOf(ship.getStartDate());
        String endDate = String.valueOf(ship.getEndDate());
        String type = ticketType.getTicketType();
        String services = additionalServices.stream().map(result-> Arrays.asList(result.getTitle())).collect(Collectors.toList()).toString();
        String excursions = excursionList.stream().map(result-> Arrays.asList(result.getTitle())).collect(Collectors.toList()).toString();


        String finalTagText = "<div class=\"ticket\">\n" +
                "            Ticket # <h14>"+ticketId+"</h14>\n" +
                "            <br>\n" +
                "            Date start trip: <h14>"+startDate+"</h14>\n" +
                "            <br>\n" +
                "            Date end trip: <h14>"+endDate+"</h14>\n" +
                "            <br>\n" +
                "            Type : <h14>"+type+"</h14>\n" +
                "            <br>\n" +
                "            Additional Services:<h14>"+services+"</h14>\n" +
                "            <br>\n" +
                "            Excursion: <h14>"+excursions+"</h14>\n" +
                "        </div>";
        try {
            pageContext.getOut().println(finalTagText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {

    }
}
