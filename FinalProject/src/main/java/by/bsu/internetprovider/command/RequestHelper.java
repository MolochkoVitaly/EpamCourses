package by.bsu.internetprovider.command;

import by.bsu.internetprovider.command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * Class RequestHelper ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RequestHelper {
    /** Field instance  */
    private static RequestHelper instance = null;

    /** Field commands  */
    HashMap<String, Command> commands = new HashMap<String, Command>();

    /**
     * Constructor RequestHelper creates a new RequestHelper instance.
     */
    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("language", new LanguageCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("forward", new ForwardCommand());
        commands.put("register", new RegistrateCommand());
        commands.put("privateOffice", new GoToPrivateOfficeCommand());
        commands.put("showAllTariffs", new ShowAllTariffsCommand());
        commands.put("showReviews", new ShowReviewsCommand());
        commands.put("showContacts", new ShowContactsCommand());
        commands.put("showAboutUs", new ShowAboutUsCommand());
        commands.put("editPersonalData", new EditPersonalDataCommand());
        commands.put("changePassword", new ChangePasswordCommand());
        commands.put("restorePassword", new RestorePasswordCommand());
    }

    /**
     * Method getCommand ...
     *
     * @param request of type HttpServletRequest
     * @return Command
     */
    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    /**
     * Method getInstance returns the instance of this RequestHelper object.
     *
     *
     *
     * @return the instance (type RequestHelper) of this RequestHelper object.
     */
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
