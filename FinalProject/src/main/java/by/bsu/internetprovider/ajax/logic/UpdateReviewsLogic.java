package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.ReviewDAO;
import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Class UpdateReviewsLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class UpdateReviewsLogic {
    /** Field REVIEWS  */
    private static final String REVIEWS = "reviews";

    /**
     * Method updateReviews ...
     * @return String
     * @throws TechnicalException when
     */
    public static String updateReviews() throws TechnicalException {
        ArrayList<Review> reviews = null;
        JSONObject object = null;
        try {
            reviews = ReviewDAO.getInstance().findAll();
            object = new JSONObject();
            object.put(REVIEWS, reviews);
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return object.toJSONString();
    }

}
