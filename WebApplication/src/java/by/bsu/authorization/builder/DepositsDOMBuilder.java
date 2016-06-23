package by.bsu.authorization.builder;


import by.bsu.authorization.entity.Bank;
import by.bsu.authorization.entity.DepositEnum;
import by.bsu.authorization.entity.EstimatedDeposit;
import by.bsu.authorization.entity.MultiDeposit;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DepositsDOMBuilder extends AbstractDepositsBuilder {
    private static final Logger LOG = Logger.getLogger(DepositsDOMBuilder.class);
    private DocumentBuilder documentBuilder;
    public  DepositsDOMBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("ParserConfigurationException", e);
        }
    }

    @Override
    public void buildSetDeposits(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse("file:/" + fileName);
            Element root =  document.getDocumentElement();
            NodeList multiDepositsList = root.getElementsByTagName("multi-deposit");
            for (int i = 0; i < multiDepositsList.getLength(); i++) {
                Element multiDepositElement = (Element)multiDepositsList.item(i);
                MultiDeposit multiDeposit = buildMultiDeposit(multiDepositElement);
                multiDeposits.add(multiDeposit);
            }
            NodeList estimatedDepositsList = root.getElementsByTagName("estimated-deposit");
            for (int i = 0; i < estimatedDepositsList.getLength(); i++) {
                Element estimatedDepositElement = (Element)estimatedDepositsList.item(i);
                EstimatedDeposit estimatedDeposit = buildEstimatedDeposit(estimatedDepositElement);
                estimatedDeposits.add(estimatedDeposit);
            }
        } catch (IOException e) {
            LOG.error("File error or I/O error: ", e);
        } catch (SAXException e){
            LOG.error("Parsing failure: ", e);
        }
    }

    private MultiDeposit buildMultiDeposit(Element multiDepositElement) {
        MultiDeposit multiDeposit = new MultiDeposit();
        // нет проверки на null, так как xsd схема не пропустит депозит без id
        multiDeposit.setId(multiDepositElement.getAttribute(DepositEnum.ACCOUNT_ID.getValue()));
        String currency = multiDepositElement.getAttribute(DepositEnum.TYPE_OF_CURRENCY.getValue());
        if (currency != null) {
            multiDeposit.setTypeOfCurrency(currency);
        }else {
            multiDeposit.setTypeOfCurrency("USD");
        }
        Bank bank = new Bank();
        Element bankElement = (Element) multiDepositElement.getElementsByTagName("bank").item(0);
        bank.setName(getElementTextContent(bankElement, DepositEnum.NAME.getValue()));
        bank.setCountry(getElementTextContent(bankElement,DepositEnum.COUNTRY.getValue()));
        multiDeposit.setBank(bank);
        multiDeposit.setDepositor(getElementTextContent(multiDepositElement, DepositEnum.DEPOSITOR.getValue()));
        multiDeposit.setAmountOnDeposit(Integer.parseInt(getElementTextContent(
                multiDepositElement, DepositEnum.AMOUNT_ON_DEPOSIT.getValue())));
        multiDeposit.setProfitability(Integer.parseInt(getElementTextContent(
                multiDepositElement, DepositEnum.PROFITABILITY.getValue())));
        multiDeposit.setTimeConstraints(Integer.parseInt(getElementTextContent(
                multiDepositElement, DepositEnum.TIME_CONSTRAINTS.getValue())));
        return  multiDeposit;
    }

    private EstimatedDeposit buildEstimatedDeposit(Element estimatedDepositElement) {
        EstimatedDeposit estimatedDeposit = new EstimatedDeposit();
        // нет проверки на null, так как xsd схема не пропустит депозит без id
        estimatedDeposit.setId(estimatedDepositElement.getAttribute(DepositEnum.ACCOUNT_ID.getValue()));

        Bank bank = new Bank();
        Element bankElement = (Element) estimatedDepositElement.getElementsByTagName("bank").item(0);
        bank.setName(getElementTextContent(bankElement, DepositEnum.NAME.getValue()));
        bank.setCountry(getElementTextContent(bankElement,DepositEnum.COUNTRY.getValue()));
        estimatedDeposit.setBank(bank);
        estimatedDeposit.setDepositor(getElementTextContent(estimatedDepositElement, DepositEnum.DEPOSITOR.getValue()));
        estimatedDeposit.setAmountOnDeposit(Integer.parseInt(getElementTextContent(
                estimatedDepositElement, DepositEnum.AMOUNT_ON_DEPOSIT.getValue())));
        estimatedDeposit.setProfitability(Integer.parseInt(getElementTextContent(
                estimatedDepositElement, DepositEnum.PROFITABILITY.getValue())));
        estimatedDeposit.setTimeConstraints(Integer.parseInt(getElementTextContent(
                estimatedDepositElement, DepositEnum.TIME_CONSTRAINTS.getValue())));
        estimatedDeposit.setMinBalance(Integer.parseInt(getElementTextContent(
                estimatedDepositElement, DepositEnum.MIN_BALANCE.getValue())));
        return estimatedDeposit;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
