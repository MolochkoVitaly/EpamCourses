package by.bsu.internetprovider.ajax.command.impl;

import by.bsu.internetprovider.ajax.command.Command;
import by.bsu.internetprovider.ajax.logic.DeleteReviewLogic;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Class DeleteReviewCommand ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class DeleteReviewCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(DeleteReviewLogic.class);

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
        LOG.info("Delete review command");
        PrintWriter out = response.getWriter();
        JSONObject object = new JSONObject();
        try {
            DeleteReviewLogic.delete(requestData);
            out.println(object);
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
