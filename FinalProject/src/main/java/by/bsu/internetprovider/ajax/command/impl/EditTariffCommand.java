package by.bsu.internetprovider.ajax.command.impl;


import by.bsu.internetprovider.ajax.command.Command;
import by.bsu.internetprovider.ajax.logic.EditTariffLogic;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class EditTariffCommand ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class EditTariffCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(EditTariffCommand.class);

    /** Field TEXT  */
    private static final String TEXT = "text";

    /** Field LANG  */
    private static final String LANG = "lang";

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
        LOG.info("Edit tariff command");
        PrintWriter out = response.getWriter();
        JSONObject object = new JSONObject();
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(LANG);
        try {
            EditTariffLogic.editTariff(requestData, lang);
            object.put(TEXT, MessageManager.getManagerByLocale(lang).getProperty(MessageManager.SUCCESS_EDIT_TARIFF));
            out.println(object);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong.", e);
            object.put(TEXT, MessageManager.getManagerByLocale(lang).getProperty(MessageManager.ERROR_EDIT_TARIFF));
            out.println(object);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (LogicException e) {
            LOG.error("LogicException", e);
            object.put(TEXT, e.getMessage());
            out.println(object);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            out.flush();
            out.close();
        }
    }
}