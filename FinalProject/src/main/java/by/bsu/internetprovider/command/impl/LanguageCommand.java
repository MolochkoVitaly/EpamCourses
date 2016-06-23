package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class LanguageCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class LanguageCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    /** Field PARAM_LANG  */
    private static final String PARAM_LANG = "lang";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    public String execute(HttpServletRequest request) {
        LOG.info("Language command");
        String page;
        HttpSession session = request.getSession(true);
        String lang = (String) request.getParameter(PARAM_LANG);
        System.out.println(lang);
        LOG.info("Add to session language parameter.");
        session.setAttribute(PARAM_LANG, lang);
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        return page;
    }
}
