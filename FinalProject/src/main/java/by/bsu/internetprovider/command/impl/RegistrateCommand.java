package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.encryption.MD5;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.LoginLogic;
import by.bsu.internetprovider.logic.RegistrationLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Class RegistrateCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RegistrateCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(RegistrateCommand.class);

    /** Field FIRST_NAME  */
    private static final String FIRST_NAME = "firstName";

    /** Field LAST_NAME  */
    private static final String LAST_NAME = "lastName";

    /** Field PATRONYMIC  */
    private static final String PATRONYMIC = "patronymic";

    /** Field PASSWORD  */
    private static final String PASSWORD = "password";

    /** Field PASSWORD_AGAIN  */
    private static final String PASSWORD_AGAIN = "passwordAgain";

    /** Field EMAIL  */
    private static final String EMAIL = "email";

    /** Field MOBILE_PHONE  */
    private static final String MOBILE_PHONE = "mobilePhone";

    /** Field ADDRESS  */
    private static final String ADDRESS = "address";

    /** Field LANG  */
    private static final String LANG = "lang";

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /** Field PARAM_ACTION_MESSAGE  */
    private static final String PARAM_ACTION_MESSAGE = "actionMessage";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    public String execute(HttpServletRequest request) {
        LOG.info("Registrate command");
        String page;
        HttpSession session = request.getSession(true);
        User user = create(request);
        String password = request.getParameter(PASSWORD);
        String passwordAgain = request.getParameter(PASSWORD_AGAIN);
        String lang = (String) session.getAttribute(LANG);
        try {
            RegistrationLogic.registrate(user, password, passwordAgain, lang);
            user = LoginLogic.checkLogin(user.getEmail(), user.getPassword());
            session.setAttribute("user", user);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_OFFICE_PAGE_PATH);
            request.setAttribute(PARAM_ACTION_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.REGISTRATION_WAS_SUCCESSFUL_MESSAGE));
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute( PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (LogicException e) {
            LOG.error("Something has gone wrong with registration.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH);
        }
        return page;
    }

    /**
     * Method create ...
     *
     * @param request of type HttpServletRequest
     * @return User
     */
    private User create(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(FIRST_NAME));
        user.setSurname(request.getParameter(LAST_NAME));
        user.setPatronymic(request.getParameter(PATRONYMIC));
        user.setPassword(MD5.encipherPassword(request.getParameter(PASSWORD)));
        user.setEmail(request.getParameter(EMAIL));
        user.setPhoneNumber(request.getParameter(MOBILE_PHONE));
        user.setAddress(request.getParameter(ADDRESS));
        user.setRole(Role.CLIENT);
        return user;
    }
}
