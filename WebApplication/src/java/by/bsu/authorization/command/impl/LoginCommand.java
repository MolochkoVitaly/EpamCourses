package by.bsu.authorization.command.impl;

import by.bsu.authorization.command.Command;
import by.bsu.authorization.manager.ConfigurationManager;
import by.bsu.authorization.manager.MessageManager;
import by.bsu.authorization.util.LoginPasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "nickname";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if (LoginPasswordValidator.isValid(login, password)) {
            request.getSession().setAttribute("user", login);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOICE_PARSER_PAGE_PATH);
        } else {
            request.getSession().setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
