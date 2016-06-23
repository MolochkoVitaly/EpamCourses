package by.bsu.internetprovider.ajax.command.impl;

import by.bsu.internetprovider.ajax.command.Command;
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
 * Class NoCommand ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class NoCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(NoCommand.class);

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
        LOG.info("No command");
        PrintWriter out = response.getWriter();
        JSONObject object = new JSONObject();
        HttpSession session = request.getSession(true);
        String lang = (String) session.getAttribute(LANG);
        try {
            object.put(TEXT, MessageManager.getManagerByLocale(lang).getProperty(MessageManager.UNSUPPORTED_COMMAND));
            out.println(object);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } finally {
            out.flush();
            out.close();
        }
    }
}
