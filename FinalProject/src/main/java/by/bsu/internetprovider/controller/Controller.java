package by.bsu.internetprovider.controller;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.command.RequestHelper;
import by.bsu.internetprovider.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class Controller ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    /** Field serialVersionUID  */
    private static final long serialVersionUID = 1L;

    /** Field LOG  */
    private static final Logger LOG = Logger.getLogger(Controller.class);

    /**
     * Method init ...
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String page;
        Command command = RequestHelper.getInstance().getCommand(req);
        page = command.execute(req);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    /**
     * Method destroy ...
     */
    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyConnections();
        super.destroy();
    }
}
