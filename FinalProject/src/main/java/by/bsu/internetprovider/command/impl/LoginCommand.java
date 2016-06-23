package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.encryption.MD5;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.LoginLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Class LoginCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class LoginCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    /** Field PARAM_USER  */
    private static final String PARAM_USER = "user";

    /** Field PARAM_NAME_EMAIL  */
    private static final String PARAM_NAME_EMAIL = "email";

    /** Field PARAM_NAME_PASSWORD  */
    private static final String PARAM_NAME_PASSWORD = "password";

    /** Field PARAM_LANG  */
    private static final String PARAM_LANG = "lang";

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    public String execute(HttpServletRequest request) {
        LOG.info("Login command");
        String page = null;
        User user = null;
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(PARAM_LANG);

        String login = request.getParameter(PARAM_NAME_EMAIL).trim();
        String password = MD5.encipherPassword(request.getParameter(PARAM_NAME_PASSWORD).trim());
        try {
            if ((user = LoginLogic.checkLogin(login, password)) != null) {
                LOG.info("Add to session user.");
                session.setAttribute(PARAM_USER, user);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            } else {
                request.setAttribute(PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                        MessageManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (LogicException e) {
            LOG.error("LogicException", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.LOGIN_BANNED_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }

        return page;
    }
}
