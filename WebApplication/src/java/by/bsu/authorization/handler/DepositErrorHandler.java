package by.bsu.authorization.handler;


import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class DepositErrorHandler extends DefaultHandler {
    private static final Logger LOG = Logger.getLogger(DepositErrorHandler.class);

    public DepositErrorHandler() throws IOException {
    }

    public void warning(SAXParseException e) {
        LOG.warn(getLineAddress(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        LOG.error(getLineAddress(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        LOG.fatal(getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
