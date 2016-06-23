package by.bsu.authorization.command.impl;

import by.bsu.authorization.builder.AbstractDepositsBuilder;
import by.bsu.authorization.builder.factory.DepositsBuilderFactory;
import by.bsu.authorization.command.Command;
import by.bsu.authorization.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ParserCommand implements Command {
    private static final String PATH = "xml/deposits.xml";
    private static final String PARSER = "parser";
    private static final String PARSER_TYPE = "parserType";
    private static final String MULTI_DEPOSIT_LIST = "multiDepositsList";
    private static final String ESTIMATED_DEPOSIT_LIST = "estimatedDepositsList";

    public String execute(HttpServletRequest request) {
        String page;
        String type = request.getParameter(PARSER).toUpperCase();
        AbstractDepositsBuilder temp = DepositsBuilderFactory.createDepositsBuilder(type);
        temp.buildSetDeposits(request.getServletContext().getRealPath("/") + PATH);
        request.getSession().setAttribute(PARSER_TYPE, type);
        request.getSession().setAttribute(MULTI_DEPOSIT_LIST, temp.getMultiDeposits());
        request.getSession().setAttribute(ESTIMATED_DEPOSIT_LIST, temp.getEstimatedDeposits());
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.XML_PARSE_RESULT_PAGE_PATH);
        return page;
    }
}
