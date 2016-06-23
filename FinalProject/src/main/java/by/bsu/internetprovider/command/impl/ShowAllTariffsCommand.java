package by.bsu.internetprovider.command.impl;


import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.ShowAllTariffsLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Class ShowAllTariffsCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowAllTariffsCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(ShowAllTariffsCommand.class);

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /** Field PARAM_ACTION_MESSAGE  */
    private static final String PARAM_ACTION_MESSAGE = "actionMessage";

    /** Field PARAM_TARIFFS  */
    private static final String PARAM_TARIFFS = "tariffs";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Show all tariffs command");
        String page;
        ArrayList<Tariff> tariffs = null;
        try {
            tariffs = ShowAllTariffsLogic.show();
            request.setAttribute(PARAM_TARIFFS, tariffs);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TARIFFS_PAGE_PATH);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
