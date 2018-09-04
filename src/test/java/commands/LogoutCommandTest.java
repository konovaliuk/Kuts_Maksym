package commands;

import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class LogoutCommandTest {

    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpServletRequest request= mock(HttpServletRequest.class);
    final HttpSession session = mock(HttpSession.class);

    @Test
    public void TestLogoutCommandExecute(){
        when(request.getSession()).thenReturn(session);
        String result = new LogoutCommand().execute(request,response);
        verify(request,times(2)).getSession();
        verify(session,times(1)).removeAttribute("user");
        verify(session,times(1)).invalidate();
        assertEquals("index.jsp",result);
    }
}