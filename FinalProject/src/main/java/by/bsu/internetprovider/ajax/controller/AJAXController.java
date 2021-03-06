package by.bsu.internetprovider.ajax.controller;


import by.bsu.internetprovider.ajax.command.Command;
import by.bsu.internetprovider.ajax.command.RequestHelper;
import by.bsu.internetprovider.ajax.util.AJAXServletUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.internetprovider.ajax.util.AJAXServletUtil.APPLICATION_JSON;
import static by.bsu.internetprovider.ajax.util.AJAXServletUtil.UTF_8;

/**
 * Class AJAXController ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
@WebServlet("/ajaxController")
public class AJAXController extends HttpServlet {
    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(AJAXController.class);

    /**
     * Method init ...
     * @throws ServletException when
     */
    @Override
    public void init() throws ServletException {
    }

    /**
     * Method doGet ...
     *
     * @param req of type HttpServletRequest
     * @param resp of type HttpServletResponse
     * @throws ServletException when
     * @throws IOException when
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("GET method");
        processRequest(req, resp);
    }

    /**
     * Method doPost ...
     *
     * @param req of type HttpServletRequest
     * @param resp of type HttpServletResponse
     * @throws ServletException when
     * @throws IOException when
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("POST method");
        processRequest(req, resp);
    }

    /**
     * Method processRequest ...
     *
     * @param req of type HttpServletRequest
     * @param resp of type HttpServletResponse
     * @throws ServletException when
     * @throws IOException when
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(APPLICATION_JSON);
        resp.setCharacterEncoding(UTF_8);
        String requestData = AJAXServletUtil.getRequestBody(req);
        Command command = RequestHelper.getInstance().getCommand(requestData);
        command.execute(req, resp, requestData);
    }
}
