package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.RestorePasswordLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Class RestorePasswordCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RestorePasswordCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(RestorePasswordCommand.class);

    /** Field RECOVERY  */
    private static final String RECOVERY = "recovery";

    /** Field PHONE_NUMBER  */
    private static final String PHONE_NUMBER = "phone";

    /** Field EMAIL  */
    private static final String EMAIL = "email";

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
        LOG.info("Restore password command");
        String page = null;
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(LANG);
        try {
            choiceRecovery(request);
            request.setAttribute(PARAM_ACTION_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.SUCCESS_RESTORE_PASSWORD));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        } catch (LogicException e) {
            LOG.error("LogicException", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;

    }

    /**
     * Method choiceRecovery ...
     *
     * @param request of type HttpServletRequest
     * @throws LogicException when
     * @throws TechnicalException when
     */
    private static void choiceRecovery(HttpServletRequest request) throws LogicException, TechnicalException {
        String recoveryType = request.getParameter(RECOVERY);
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(LANG);
        switch (recoveryType.toUpperCase()) {
            case "SMS":
                String phoneNumber = request.getParameter(PHONE_NUMBER);
                RestorePasswordLogic.restoreBySMS(phoneNumber, lang);
                break;
            case "EMAIL":
                String email = request.getParameter(EMAIL);
                RestorePasswordLogic.restoreByEmail(email, lang);
                break;
            default:
                throw new LogicException("Unknown recovery type");
        }
    }
}
