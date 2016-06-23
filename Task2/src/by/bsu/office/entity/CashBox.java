package by.bsu.office.entity;


import by.bsu.office.util.ClientSwapper;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashBox extends Thread{
    private static final Logger LOG = Logger.getLogger(CashBox.class);
    private static final int MAX_CAPACITY = 3;

    private int cashBoxId;
    private ArrayDeque<Client> clientsQueue;
    private ClientSwapper swapper;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public CashBox (int id, ClientSwapper clientSwapper) {
        this.swapper = clientSwapper;
        this.clientsQueue = new ArrayDeque<>(MAX_CAPACITY);
        this.cashBoxId = id;
    }

    public int getCashBoxId() {
        return cashBoxId;
    }

    public void setSwapper(ClientSwapper swapper) {
        this.swapper = swapper;
    }

    public Client take() {
        lock.lock();
        if (clientsQueue.isEmpty()) {
            try {
                LOG.info("Queue in cash box № " + (getCashBoxId() + 1) + " is empty/ is waiting clients");
                condition.await();
            } catch (InterruptedException e) {
                LOG.error("InterruptedException", e);
            }
        } else if (isFull()) {
            condition.signal();
        }
        Client client = null;
        try {
            client = clientsQueue.poll();
            LOG.info("Client № " + client.getClientId() + " is served by cash box № " + (getCashBoxId() + 1));
            client.setServed(true);
        } finally {
            lock.unlock();
        }
        return client;
    }

    public void put(Client client) {
        lock.lock();
        if (isFull()) {
            try {
                LOG.info("Queue in cash box № " + (getCashBoxId() + 1) + " is full, client № "
                        + client.getClientId() + " is waiting a free place");
                condition.await();
            } catch (InterruptedException e) {
                LOG.error("InterruptedException", e);
            }
        }
        try {
            clientsQueue.add(client);
            condition.signal();
            LOG.info("Client № " + client.getClientId() + " came in queue in cash box № " + (getCashBoxId() + 1));
        } finally {
            lock.unlock();
        }
    }

    public boolean checkFreePlace(Client client) {
        lock.lock();
        boolean result = false;
        try {
            if (!isFull()) {
                client.start();
                put(client);
                result = true;
            }
        } finally {
            lock.unlock();
        }
        return result;
    }

    public boolean isFull() {
        return (clientsQueue.size() == MAX_CAPACITY);
    }

    @Override
    public void run() {
        while (true) {
            try {
                take();
                transfer();
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                LOG.error("InterruptedException", e);
            }
        }
    }

    private void transfer() {
        try {
            lock.lock();
            clientsQueue.stream().filter(client -> client != null).filter(Client::isWantToReplace).forEach(client -> {
                client.setReplace(false);
                swapper.exchanger(client);
            });
        } finally {
            lock.unlock();
        }
    }
}
