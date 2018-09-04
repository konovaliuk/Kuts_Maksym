package commands;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.internal.PowerMockitoCore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ChangeLanguageCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);

    @Test
    public void TestChangeLanguageCommand() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("language")).thenReturn("uk");

        String result = new ChangeLanguageCommand().execute(request,response);

        verify(request,times(1)).getParameter("language");
        verify(request.getSession(),times(1)).setAttribute("lang","uk");
        assertEquals("index.jsp",result);

    }


}