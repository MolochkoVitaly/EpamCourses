package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.EditPersonalDataLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Class EditPersonalDataCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class EditPersonalDataCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(EditPersonalDataCommand.class);

    /** Field SURNAME  */
    private static final String SURNAME = "surname";

    /** Field EMAIL  */
    private static final String EMAIL = "email";

    /** Field PHONE  */
    private static final String PHONE = "phone";

    /** Field PASSPORT  */
    private static final String PASSPORT = "passport";

    /** Field ADDRESS  */
    private static final String ADDRESS = "address";

    /** Field USER  */
    private static final String USER = "user";

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
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Edit personal data command");
        String page = null;
        HttpSession session = request.getSession();
        User user = null;
        User newData = null;
        String lang = (String) session.getAttribute(LANG);
        try {
            user = (User)session.getAttribute(USER);
            newData = create(request);
            EditPersonalDataLogic.editData(user, newData, lang);
            session.setAttribute(USER, user);
            request.setAttribute(PARAM_ACTION_MESSAGE, MessageManager.getManagerByLocale(lang).getProperty(
                    MessageManager.SUCCESS_CHANGE_PERSONAL_DATA));
            page = ConfigurationManager.getInstance().getProperty(
                   user.getRole() == Role.ADMIN ? ConfigurationManager.ADMIN_OFFICE_PAGE_PATH :
                   ConfigurationManager.USER_OFFICE_PAGE_PATH);
        } catch (LogicException e) {
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(
                    user.getRole() == Role.ADMIN ? ConfigurationManager.ADMIN_OFFICE_PAGE_PATH :
                            ConfigurationManager.USER_OFFICE_PAGE_PATH);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
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
        user.setSurname(request.getParameter(SURNAME).trim());
        user.setEmail(request.getParameter(EMAIL).trim());
        user.setPhoneNumber(request.getParameter(PHONE).trim());
        user.setAddress(request.getParameter(ADDRESS).trim());
        return user;
    }
}
