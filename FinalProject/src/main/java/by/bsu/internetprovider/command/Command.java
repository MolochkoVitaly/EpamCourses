package by.bsu.internetprovider.command;


import javax.servlet.http.HttpServletRequest;
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
     * @return String
     */
    String execute(HttpServletRequest request);
}
