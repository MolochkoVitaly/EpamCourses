package by.bsu.internetprovider.command.impl;

import by.bsu.internetprovider.command.Command;
import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.logic.ShowReviewsLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Class ShowReviewsCommand ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowReviewsCommand implements Command {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(ShowReviewsCommand.class);

    /** Field PARAM_ERROR_MESSAGE  */
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /** Field PARAM_ACTION_MESSAGE  */
    private static final String PARAM_ACTION_MESSAGE = "actionMessage";

    /** Field PARAM_REVIEWS  */
    private static final String PARAM_REVIEWS = "reviews";

    /**
     * Method execute ...
     *
     * @param request of type HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Show reviews command");
        String page;
        ArrayList<Review> reviews = null;
        try {
            reviews = ShowReviewsLogic.show();
            request.setAttribute(PARAM_REVIEWS, reviews);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REVIEWS_PAGE_PATH);
        } catch (TechnicalException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, e);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
