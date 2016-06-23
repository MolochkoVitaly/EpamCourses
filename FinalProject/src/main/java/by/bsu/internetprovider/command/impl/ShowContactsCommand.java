package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Class ShowContactsCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowContactsCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(ShowContactsCommand.class);


    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Show contacts command");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CONTACTS_PAGE_PATH);
    }
}
