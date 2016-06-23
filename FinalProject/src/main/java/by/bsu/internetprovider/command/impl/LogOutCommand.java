package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class LogOutCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class LogOutCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(LogOutCommand.class);

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    public String execute(HttpServletRequest request) {
        LOG.info("Logout command");
        HttpSession session = request.getSession();
        LOG.info("Finish session.");
        session.invalidate();
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
