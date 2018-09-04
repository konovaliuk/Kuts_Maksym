package commands;

import enteties.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AuthorizationService;
import utils.PasswordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PasswordUtils.class, AuthorizationService.class})
public class RegisterCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final User user = new User();

    @Before
    public void setUp(){
        user.setUsername("username");
        user.setPassword("cryptedPassword");
        user.setEmail("email");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        PowerMockito.mockStatic(PasswordUtils.class);
        when(PasswordUtils.cryptWithMD5("password")).thenReturn("cryptedPassword");
        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        PowerMockito.mockStatic(AuthorizationService.class);
    }

    @Test
    public void TestRegisterCommandExecuteWhenRegisterSuccessful() throws Exception {


        when(AuthorizationService.registerUser(user)).thenReturn(null);

        assertEquals("index.jsp",new RegisterCommand().execute(request,response));

    }
    @Test
    public void TestRegisterCommandExecuteWhenRegisterIsUnsuccessful() throws Exception {


        when(AuthorizationService.registerUser(user)).thenReturn("emailRegistered");

        String result = new RegisterCommand().execute(request,response);
        verify(request,times(1)).setAttribute("message", "emailRegistered");
        verify(request,times(1)).setAttribute("username", user.getUsername());
        verify(request,times(1)).setAttribute("userEmail", user.getEmail());
        verify(request,times(1)).setAttribute("firstName", user.getFirstName());
        verify(request,times(1)).setAttribute("lastName", user.getLastName());
        assertEquals("register.jsp",result);

    }
}