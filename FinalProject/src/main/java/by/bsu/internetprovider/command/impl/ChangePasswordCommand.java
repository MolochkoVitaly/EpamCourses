package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.encryption.MD5;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.ChangePasswordLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class ChangePasswordCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ChangePasswordCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(ChangePasswordCommand.class);

    /** Field NEW_PASSWORD  */
    private static final String PASSWORD = "password";

    /** Field NEW_PASSWORD  */
    private static final String PASSWORD_AGAIN = "passwordAgain";

    /** Field NEW_PASSWORD  */
    private static final String NEW_PASSWORD = "newPassword";

    /** Field USER  */
    private static final String USER = "user";

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /** Field PARAM_ACTION_MESSAGE  */
    private static final String PARAM_ACTION_MESSAGE = "actionMessage";

    /** Field LANG  */
    private static final String LANG = "lang";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Change password command");
        String page = null;
        String newPassword = null;
        String password = null;
        String passwordAgain = null;
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(LANG);
        try {
            password = (request.getParameter(PASSWORD)).trim();
            passwordAgain = (request.getParameter(PASSWORD_AGAIN)).trim();
            newPassword = (request.getParameter(NEW_PASSWORD)).trim();
            User user = (User)request.getSession().getAttribute(USER);
            ChangePasswordLogic.change(user,password, passwordAgain, newPassword, lang);
            user.setPassword(MD5.encipherPassword(newPassword));
            request.getSession().setAttribute(USER, user);
            request.setAttribute(PARAM_ACTION_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.SUCCESS_CHANGE_PASSWORD));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_OFFICE_PAGE_PATH);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (LogicException e) {
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_OFFICE_PAGE_PATH);
        }
        return page;
    }
}
