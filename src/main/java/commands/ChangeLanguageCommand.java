package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> acceptableLanguages = new ArrayList<>();
        acceptableLanguages.add("uk");
        acceptableLanguages.add("us");

        String language = request.getParameter("language");

        if(acceptableLanguages.contains(language)){
            request.getSession().setAttribute("lang",language);
        }

        return "index.jsp";
    }
}
