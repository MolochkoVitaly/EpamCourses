package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.ReviewDAO;
import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToReviewText;
import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;

/**
 * Class AddReviewLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class AddReviewLogic {

    /**
     * Method add ...
     *
     * @param requestData of type String
     * @param user of type User
     * @throws TechnicalException when
     */
    public static void add (String requestData, User user) throws TechnicalException {
        try {
            Review review = new Review();
            review.setUser(user);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            review.setDate(format.format(date));
            JSONObject json = stringToJson(requestData);
            review.setText(jsonToReviewText(json));
            ReviewDAO.getInstance().add(review);
        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }
}
