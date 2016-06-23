package by.bsu.internetprovider.ajax.command.impl;


import by.bsu.internetprovider.ajax.command.Command;
import by.bsu.internetprovider.ajax.logic.FindSelectedTariffLogic;
import by.bsu.internetprovider.exception.TechnicalException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class FindSelectedTariffCommand ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class FindSelectedTariffCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(FindSelectedTariffCommand.class);

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @param response of type HttpServletResponse
     * @param requestData of type String
     * @throws IOException when
     * @see Command#execute(HttpServletRequest, HttpServletResponse, String)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String requestData) throws IOException {
        LOG.info("Find selected tariff command");
        PrintWriter out = response.getWriter();
        try {
            String tariff = FindSelectedTariffLogic.findSelected(requestData);
            out.println(tariff);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong.", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            out.flush();
            out.close();
        }
    }
}
