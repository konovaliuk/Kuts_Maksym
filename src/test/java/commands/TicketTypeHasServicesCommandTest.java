package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdminPageService.class)
public class TicketTypeHasServicesCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final PrintWriter writer = mock(PrintWriter.class);

    @Test
    public void TestCommandExecute() throws Exception {
        when(request.getParameter("ship_id")).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);
        PowerMockito.mockStatic(AdminPageService.class);
        HashMap<Integer, List<Integer>> result = new HashMap<>();
        List<Integer> servicesId = new ArrayList<>();
        servicesId.add(1);
        servicesId.add(2);
        result.put(1,servicesId);
        when(AdminPageService.getTypeServiceDependencyByShipId(1l)).thenReturn(result);

        String jsonString = new Gson().toJson(result);

        Gson gson = new Gson();
        PowerMockito.whenNew(Gson.class).withNoArguments().thenReturn(gson);

        when(new Gson().toJson(result)).thenReturn(jsonString);

        String execute = new TicketTypeHasServicesCommand().execute(request,response);

        verify(writer,times(1)).print(jsonString);
        assertEquals("ajax",execute);

    }
}