package by.bsu.authorization.builder;


import by.bsu.authorization.entity.EstimatedDeposit;
import by.bsu.authorization.entity.MultiDeposit;
import by.bsu.authorization.handler.DepositHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class DepositsSAXBuilder extends AbstractDepositsBuilder{
    private static final Logger LOG = Logger.getLogger(DepositsSAXBuilder.class);
    private DepositHandler depositHandler;
    private XMLReader reader;

    public DepositsSAXBuilder() {
        super();
        try {
            depositHandler = new DepositHandler();
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            LOG.error("SAX parse error", e);
        }
    }


    @Override
    public void buildSetDeposits(String fileName) {
        try {
            reader.setContentHandler(depositHandler);
            reader.parse("file:/" + fileName);
        } catch (SAXException e) {
            LOG.error("SAX parse error", e);
        } catch (IOException e) {
            LOG.error("IO stream error ", e);
        }

        multiDeposits = depositHandler.getMultiDeposits();
        estimatedDeposits = depositHandler.getEstimatedDeposit();
    }

}
