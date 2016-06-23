package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;

/**
 * Class LoginLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class LoginLogic {
    /**
     * Method checkLogin ...
     *
     * @param login of type String
     * @param password of type String
     * @return User
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static User checkLogin(String login, String password) throws TechnicalException, LogicException {
        User user = null;
        ClientAccount account = null;
        try {
            user = UserDAO.getInstance().findByLoginPasswordInClients(login, password);
            if (user == null) {
                user = UserDAO.getInstance().findByLoginPasswordInAdministrators(login, password);
            } else {
                account = ClientsAccountDAO.getInstance().findById(user.getId());
                if (account.getEndDate() !=  null) {
                    throw new LogicException();
                }
            }
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return user;
    }
}
