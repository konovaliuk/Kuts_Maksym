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

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PasswordUtils.class,AuthorizationService.class})
public class LoginCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);
    @Before
    public void setUp(){
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("password");
        PowerMockito.mockStatic(PasswordUtils.class);
        when(PasswordUtils.cryptWithMD5("password")).thenReturn("cryptedPassword");
        PowerMockito.mockStatic(AuthorizationService.class);
    }

    @Test
    public void TestLoginCommandExecuteWhenUserIsNull(){


        when(AuthorizationService.getUserIfRegistered("username","cryptedPassword")).thenReturn(null);

        String result = new LoginCommand().execute(request,response);
        verify(request,times(1)).setAttribute("UserUsername","username");
        verify(request,times(1)).setAttribute("message","userNotFound");
        assertEquals("login.jsp",result);
    }
    @Test
    public void TestLoginCommandExecuteWhenUserIsNotNull(){
        User user = new User();

        when(request.getSession()).thenReturn(session);
        when(AuthorizationService.getUserIfRegistered("username","cryptedPassword")).thenReturn(user);

        String result = new LoginCommand().execute(request,response);
        verify(request,times(1)).getSession();
        verify(session,times(1)).setAttribute("user",user);
        assertEquals("index.jsp",result);
    }
}