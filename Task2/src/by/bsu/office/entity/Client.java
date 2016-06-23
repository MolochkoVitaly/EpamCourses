package by.bsu.office.entity;

import org.apache.log4j.Logger;

import java.util.Random;

public class Client extends Thread {
    private static final Logger LOG = Logger.getLogger(Client.class);
    private final int PERCENT_CLIENTS_TO_TRANSFER = 70;
    private final int ALL_PERCENT = 100;
    private int clientId;
    private int cashBoxId;
    private boolean replace;
    private boolean served;

    public Client(int id) {
        this.clientId = id;
        this.replace = false;
        this.served = false;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(int cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public boolean isWantToReplace() {
        return replace;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    @Override
    public void run() {
        if (!served) {
            generateChoiceOtherQueue();
        }
        if (replace) {
            LOG.info("Client № " + clientId + " wants to transfer from cash box № " + (cashBoxId + 1) + " to another queue");
        }
    }

    private boolean generateChoiceOtherQueue() {
        replace = (new Random()).nextInt(ALL_PERCENT) > PERCENT_CLIENTS_TO_TRANSFER;
        return replace;
    }
}
