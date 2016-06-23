package by.bsu.internetprovider.logic;

import by.bsu.internetprovider.dao.impl.ReviewDAO;
import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;

import java.util.ArrayList;


/**
 * Class ShowReviewsLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowReviewsLogic {
    /**
     * Method show ...
     * @return ArrayList<Review>
     * @throws TechnicalException when
     */
    public static ArrayList<Review> show() throws TechnicalException {
        ArrayList<Review> reviews = null;
        try {
            reviews = ReviewDAO.getInstance().findAll();
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return reviews;
    }
}
