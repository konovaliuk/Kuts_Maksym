package commands;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MissCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void TestMissCommandExecute(){
        assertEquals("index.jsp",new MissCommand().execute(request,response));
    }
}