package commands;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdminPageService.class)
public class GetShipListCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final PrintWriter writer = mock(PrintWriter.class);
    @Test
    public void TestGetShipListCommand() throws Exception {
        when(response.getWriter()).thenReturn(writer);
        PowerMockito.mockStatic(AdminPageService.class);
        when(AdminPageService.getAllShips()).thenReturn(new ArrayList<>());

        Gson gson = new Gson();
        PowerMockito.whenNew(Gson.class).withNoArguments().thenReturn(gson);

        when(new Gson().toJson("")).thenReturn("test");
        doNothing().when(writer).print(anyString());

        String result = new GetShipListCommand().execute(request,response);
        assertEquals("ajax",result);
    }
}