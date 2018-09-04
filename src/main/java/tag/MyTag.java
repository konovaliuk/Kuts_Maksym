package tag;


import dao.DataBaseFactory;
import dao.ShipDAO;
import enteties.Ship;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.List;


public class MyTag implements Tag {
    PageContext pageContext;

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
        ShipDAO shipDAO = DataBaseFactory.getShipDAO();
        List<Ship> ships = shipDAO.findAllShips();
        try {
            for (Ship item : ships) {
                String toOut = "<a href=\"/ship?shipId=" + item.getId() + "\"><div class=\"cruise_element\" style=\"background-image: url(images/cruise_" + item.getId() + ".jpg)\">\n" +
                        "                <h10>" + item.getTitle() + "</h10>\n" +
                        "                <h11>" + item.getPrice() + "</h11>\n" +
                        "                </div></a><br>";
                pageContext.getOut().println(toOut);

            }

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
