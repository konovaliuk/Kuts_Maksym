package commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.AdminPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdminPageService.class)
public class UpdateTypeHasServicesCommandTest {

    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final PrintWriter writer = mock(PrintWriter.class);

    @Test
    public void TestCommandExecute() throws Exception {
        when(request.getParameter("json")).thenReturn("{\"1\":[1,2]}");
        when(request.getParameter("ship_id")).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);
        Gson gson = new Gson();
        PowerMockito.whenNew(Gson.class).withNoArguments().thenReturn(gson);

        Type type = new TypeToken<HashMap<Integer,List<String>>>(){}.getType();
        HashMap<Integer, List<Integer>> toUpdate = gson.fromJson("{\"1\":[1,2]}",type);

        PowerMockito.mockStatic(AdminPageService.class);

        String result = new UpdateTypeHasServicesCommand().execute(request,response);

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        AdminPageService.updateTypeServiceDependency(toUpdate,1l);
        PowerMockito.verifyNoMoreInteractions(AdminPageService.class);

        verify(writer,times(1)).print(anyString());

        assertEquals("ajax",result);

    }

}