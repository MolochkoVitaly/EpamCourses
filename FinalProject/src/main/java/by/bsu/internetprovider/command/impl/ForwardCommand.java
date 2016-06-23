package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Class ForwardCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ForwardCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(ForwardCommand.class);

    /** Field PARAM_LOGIN_PAGE  */
    private static final String PARAM_LOGIN_PAGE = "toLogin";

    /** Field PARAM_REGISTRATION_PAGE  */
    private static final String PARAM_REGISTRATION_PAGE = "toRegister";

    /** Field PARAM_PAGE  */
    private static final String PARAM_PAGE = "forward";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Forward command");
        String page;
        page = checkPage(request);
        return page;
    }

    /**
     * Method checkPage ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    private String checkPage(HttpServletRequest request) {
        String pageParam = request.getParameter(PARAM_PAGE);
        switch (pageParam) {
            case PARAM_LOGIN_PAGE:
                return ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.LOGIN_PAGE_PATH);
            case PARAM_REGISTRATION_PAGE:
                return ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.REGISTRATION_PAGE_PATH);
            default:
                return ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.MAIN_PAGE_PATH);
        }
    }
}
