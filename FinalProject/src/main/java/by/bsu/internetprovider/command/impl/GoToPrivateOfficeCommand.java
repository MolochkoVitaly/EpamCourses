package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.entity.*;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.ShowBalanceLogic;
import by.bsu.internetprovider.logic.ShowUsedTariffsLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Class GoToPrivateOfficeCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class GoToPrivateOfficeCommand implements Command{
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(GoToPrivateOfficeCommand.class);

    /** Field PARAM_USED_TARIFF  */
    private static final String PARAM_USED_TARIFF = "usedTariff";

    /** Field PARAM_ORDERS  */
    private static final String PARAM_ORDERS = "orders";

    /** Field PARAM_BALANCE  */
    private static final String PARAM_BALANCE = "balance";

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /** Field PARAM_ACTION_MESSAGE  */
    private static final String PARAM_ACTION_MESSAGE = "actionMessage";

    /** Field PARAM_USER  */
    private static final String PARAM_USER = "user";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Go to private office command");
        String page = null;
        User user = null;
        Long balance = null;
        AccountInfo usedTariff = null;
        try {
            user = (User)request.getSession().getAttribute(PARAM_USER);
            if (user.getRole() == Role.CLIENT) {
                balance = ShowBalanceLogic.showBalance(user.getId());
                usedTariff = ShowUsedTariffsLogic.show(user.getId());
                request.setAttribute(PARAM_BALANCE, balance);
                request.setAttribute(PARAM_USED_TARIFF, usedTariff);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_OFFICE_PAGE_PATH);
            } else if (user.getRole() == Role.ADMIN) {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_OFFICE_PAGE_PATH);
            } else {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            }
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
