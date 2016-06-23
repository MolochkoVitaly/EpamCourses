package by.bsu.authorization.handler;

import by.bsu.authorization.entity.EstimatedDeposit;
import by.bsu.authorization.entity.MultiDeposit;
import by.bsu.authorization.entity.DepositEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DepositHandler extends DefaultHandler {
    private Set<MultiDeposit> multiDeposits;
    private Set<EstimatedDeposit> estimatedDeposits;
    private MultiDeposit currentMultiDeposit;
    private EstimatedDeposit currentEstimatedDeposit;
    private DepositEnum currentEnum;
    private DepositEnum currentDeposit;
    private EnumSet<DepositEnum> withText;

    public DepositHandler() {
        multiDeposits = new HashSet<MultiDeposit>();
        estimatedDeposits = new HashSet<EstimatedDeposit>();
        withText = EnumSet.range(DepositEnum.NAME, DepositEnum.MIN_BALANCE);
    }

    public Set<MultiDeposit> getMultiDeposits() {
        return multiDeposits;
    }

    public Set<EstimatedDeposit> getEstimatedDeposit() {
        return estimatedDeposits;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        DepositEnum temp = DepositEnum.valueOf(localName.toUpperCase().replace("-", "_"));
        switch (temp) {
            case MULTI_DEPOSIT:
                currentDeposit = DepositEnum.MULTI_DEPOSIT;
                currentMultiDeposit = new MultiDeposit();
                currentMultiDeposit.setId(attributes.getValue(0));
                String currency = attributes.getValue(1);
                if (currency != null) {
                    currentMultiDeposit.setTypeOfCurrency(attributes.getValue(1));
                }else {
                    currentMultiDeposit.setTypeOfCurrency("USD");
                }
                break;
            case ESTIMATED_DEPOSIT:
                currentDeposit = DepositEnum.ESTIMATED_DEPOSIT;
                currentEstimatedDeposit = new EstimatedDeposit();
                currentEstimatedDeposit.setId(attributes.getValue(0));
                break;
            default:
                temp = DepositEnum.valueOf(localName.toUpperCase().replace("-", "_"));
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) {
        DepositEnum temp = DepositEnum.valueOf(localName.toUpperCase().replace("-", "_"));
        switch (temp) {
            case MULTI_DEPOSIT:
                multiDeposits.add(currentMultiDeposit);
                break;
            case ESTIMATED_DEPOSIT:
                estimatedDeposits.add(currentEstimatedDeposit);
                break;
            default:
                break;
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentDeposit) {
                case MULTI_DEPOSIT:
                    switch (currentEnum) {
                        case NAME:
                            currentMultiDeposit.getBank().setName(s);
                            break;
                        case COUNTRY:
                            currentMultiDeposit.getBank().setCountry(s);
                            break;
                        case DEPOSITOR:
                            currentMultiDeposit.setDepositor(s);
                            break;
                        case AMOUNT_ON_DEPOSIT:
                            currentMultiDeposit.setAmountOnDeposit(Integer.parseInt(s));
                            break;
                        case PROFITABILITY:
                            currentMultiDeposit.setProfitability(Integer.parseInt(s));
                            break;
                        case TIME_CONSTRAINTS:
                            currentMultiDeposit.setTimeConstraints(Integer.parseInt(s));
                            break;
                        default:
                            //Unsupported xml tag
                            break;
                    }
                    break;
                case ESTIMATED_DEPOSIT:
                    switch (currentEnum) {
                        case NAME:
                            currentEstimatedDeposit.getBank().setName(s);
                            break;
                        case COUNTRY:
                            currentEstimatedDeposit.getBank().setCountry(s);
                            break;
                        case DEPOSITOR:
                            currentEstimatedDeposit.setDepositor(s);
                            break;
                        case AMOUNT_ON_DEPOSIT:
                            currentEstimatedDeposit.setAmountOnDeposit(Integer.parseInt(s));
                            break;
                        case PROFITABILITY:
                            currentEstimatedDeposit.setProfitability(Integer.parseInt(s));
                            break;
                        case TIME_CONSTRAINTS:
                            currentEstimatedDeposit.setTimeConstraints(Integer.parseInt(s));
                            break;
                        case MIN_BALANCE:
                            currentEstimatedDeposit.setMinBalance(Integer.parseInt(s));
                            break;
                        default:
                            //Unsupported xml tag
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        currentEnum = null;
    }
}
