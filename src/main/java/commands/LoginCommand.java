package commands;

import enteties.User;
import service.AuthorizationService;
import utils.PasswordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = PasswordUtils.cryptWithMD5(request.getParameter("password"));

        User user = AuthorizationService.getUserIfRegistered(username, password);

        if (user == null) {
            request.setAttribute("UserUsername",username);
            request.setAttribute("message","userNotFound");
            return "login.jsp";
        }else {
            request.getSession().setAttribute("user",user);
            return "index.jsp";
        }

    }
}
