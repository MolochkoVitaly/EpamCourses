package by.bsu.authorization.command;

import by.bsu.authorization.command.impl.LanguageCommand;
import by.bsu.authorization.command.impl.LoginCommand;
import by.bsu.authorization.command.impl.NoCommand;
import by.bsu.authorization.command.impl.ParserCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public class RequestHelper {
    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("language", new LanguageCommand());
        commands.put("parser", new ParserCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
