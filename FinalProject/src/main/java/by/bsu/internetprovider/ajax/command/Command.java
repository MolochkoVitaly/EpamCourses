package by.bsu.internetprovider.ajax.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface Command ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface Command {

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @param response of type HttpServletResponse
     * @param requestData of type String
     * @throws IOException when
     */
    void execute(HttpServletRequest request, HttpServletResponse response, String requestData) throws IOException;
}
