package by.bsu.authorization.command.impl;


import by.bsu.authorization.command.Command;
import by.bsu.authorization.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);
    private static final String PARAM_LANG = "lang";

    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        String lang = (String) request.getParameter(PARAM_LANG);
        LOG.info("Add to session language parameter.");
        session.setAttribute(PARAM_LANG, lang);
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        return page;
    }
}
