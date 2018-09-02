package commands;

import enteties.User;
import service.AuthorizationService;
import utils.PasswordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(PasswordUtils.cryptWithMD5(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));

        String message = AuthorizationService.registerUser(user);
        if( message != null){
            request.setAttribute("message",message);
            request.setAttribute("username",user.getUsername());
            request.setAttribute("userEmail",user.getEmail());
            request.setAttribute("firstName",user.getFirstName());
            request.setAttribute("lastName",user.getLastName());
            return "register.jsp";
        }
        return "index.jsp";
    }
}
