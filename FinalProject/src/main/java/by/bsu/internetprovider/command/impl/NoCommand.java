package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Class NoCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class NoCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(by.bsu.internetprovider.ajax.command.impl.NoCommand.class);

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    public String execute(HttpServletRequest request) {
        LOG.info("No command");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
