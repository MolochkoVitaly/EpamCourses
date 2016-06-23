package by.bsu.authorization.builder;

import by.bsu.authorization.entity.*;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DepositsStAXBuilder extends AbstractDepositsBuilder {
    private static final Logger LOG = Logger.getLogger(DepositsStAXBuilder.class);
    private XMLInputFactory inputFactory;

    public DepositsStAXBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetDeposits(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase().replace("-", "_")) == DepositEnum.MULTI_DEPOSIT) {
                        MultiDeposit multiDeposit  = buildMultiDeposit(reader);
                        multiDeposits.add(multiDeposit);
                    } else if (DepositEnum.valueOf(name.toUpperCase().replace("-", "_")) == DepositEnum.ESTIMATED_DEPOSIT) {
                        EstimatedDeposit estimatedDeposit =buildEstimatedDeposit(reader);
                        estimatedDeposits.add(estimatedDeposit);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOG.error("StAX parsing error", e);
        } catch (FileNotFoundException e) {
            LOG.error("File " + fileName + "not found", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("Impossible close file " + fileName, e );
            }
        }
    }

    private MultiDeposit buildMultiDeposit(XMLStreamReader reader) throws XMLStreamException {
        MultiDeposit multiDeposit = new MultiDeposit();
        String id = reader.getAttributeValue(null, DepositEnum.ACCOUNT_ID.getValue());
        multiDeposit.setId(id);
        String currency = reader.getAttributeValue(null, DepositEnum.TYPE_OF_CURRENCY.getValue());
        if (currency == null) {
            multiDeposit.setTypeOfCurrency("USD");
        } else {
            multiDeposit.setTypeOfCurrency(currency);
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case BANK:
                            multiDeposit.setBank(getXMLBank(reader));
                            break;
                        case DEPOSITOR:
                            multiDeposit.setDepositor(getXMLText(reader));
                            break;
                        case AMOUNT_ON_DEPOSIT:
                            multiDeposit.setAmountOnDeposit(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PROFITABILITY:
                            multiDeposit.setProfitability(Integer.parseInt(getXMLText(reader)));
                            break;
                        case TIME_CONSTRAINTS:
                            multiDeposit.setTimeConstraints(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase().replace("-", "_")) == DepositEnum.MULTI_DEPOSIT) {
                        return  multiDeposit;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag MultiDeposit");
    }

    private EstimatedDeposit buildEstimatedDeposit(XMLStreamReader reader) throws XMLStreamException {
        EstimatedDeposit estimatedDeposit = new EstimatedDeposit();
        estimatedDeposit.setId(reader.getAttributeValue(null, DepositEnum.ACCOUNT_ID.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case BANK:
                            estimatedDeposit.setBank(getXMLBank(reader));
                            break;
                        case DEPOSITOR:
                            estimatedDeposit.setDepositor(getXMLText(reader));
                            break;
                        case AMOUNT_ON_DEPOSIT:
                            estimatedDeposit.setAmountOnDeposit(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PROFITABILITY:
                            estimatedDeposit.setProfitability(Integer.parseInt(getXMLText(reader)));
                            break;
                        case TIME_CONSTRAINTS:
                            estimatedDeposit.setTimeConstraints(Integer.parseInt(getXMLText(reader)));
                            break;
                        case MIN_BALANCE:
                            estimatedDeposit.setMinBalance(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase().replace("-", "_")) == DepositEnum.ESTIMATED_DEPOSIT) {
                        return  estimatedDeposit;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag EstimatedDeposit");
    }

    private Bank getXMLBank(XMLStreamReader reader) throws XMLStreamException {
        Bank bank = new Bank();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case NAME:
                            bank.setName(getXMLText(reader));
                            break;
                        case COUNTRY:
                            bank.setCountry(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase().replace("-","_")) == DepositEnum.BANK) {
                        return bank;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Bank");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

