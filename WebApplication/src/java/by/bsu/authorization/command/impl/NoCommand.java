package by.bsu.authorization.command.impl;

import by.bsu.authorization.command.Command;
import by.bsu.authorization.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NoCommand implements Command {
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}
