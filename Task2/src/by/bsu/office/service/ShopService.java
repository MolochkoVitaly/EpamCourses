package by.bsu.office.service;


import by.bsu.office.entity.CashBox;
import by.bsu.office.entity.Client;
import by.bsu.office.util.ClientSwapper;
import by.bsu.office.util.IdGenerator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ShopService {
    private static final int COUNT_CASH_BOX = 2;
    private static final Logger LOG = Logger.getLogger(ShopService.class);
    private static ShopService instance = null;
    private static Lock lock = new ReentrantLock();
    private final int MAX_COUNT_CLIENTS = 20;

    private  ArrayList<CashBox> listCashBoxes;
    private Random random;
    private  ClientSwapper swapper;

    private ShopService() {
        this.listCashBoxes = new ArrayList<>();
        this.random = new Random();
        ClientSwapper swapper = new ClientSwapper();
        setSwapper(swapper);
        for (int i = 0; i < COUNT_CASH_BOX; i++) {
            listCashBoxes.add((new CashBox(i, swapper)));
        }
    }

    public static ShopService getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ShopService();
                instance.open();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private void setSwapper(ClientSwapper swapper) {
        this.swapper = swapper;
        for (CashBox box : listCashBoxes) {
            box.setSwapper(this.swapper);
        }
    }

    private void open() {
        LOG.info("Shop opened, cash boxes begin their work");
        for (int i = 0; i < COUNT_CASH_BOX; i++) {
            listCashBoxes.get(i).start();
        }
    }

    public void work() {
        int countClients = random.nextInt(MAX_COUNT_CLIENTS);
        for (int i = 0; i < countClients; i++) {
            toServe(new Client(IdGenerator.getId()));
        }
    }

    private void toServe(Client client) {
        LOG.info("Choice new cash box");
        int num;
        do {
            num = generateNumberAllCashBox();
            client.setCashBoxId(num);
        } while (!listCashBoxes.get(num).checkFreePlace(client));
    }

    private int generateNumberAllCashBox() {
        return random.nextInt(COUNT_CASH_BOX);
    }
}
