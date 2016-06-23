package by.bsu.office.util;

import by.bsu.office.entity.Client;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ClientSwapper{
    private static final Logger LOG = Logger.getLogger(ClientSwapper.class);

    private Client client;
    private Lock lock = new ReentrantLock();

    public ClientSwapper() {
    }

    public void exchanger(Client swap) {
        try {
            lock.lock();
            if (client != null && client.isServed()){
                LOG.info("Client № " + client.getClientId() + " was served earlier than changed with client from another queue");
                clear();
            } else {
                if (client != null && !client.isServed()) {
                    swapInQueue(swap);
                    clear();
                }
            }
            add(swap);
        } finally {
            lock.unlock();
        }
    }

    private void add(Client client) {
        try {
            lock.lock();
            this.client = client;
        } finally {
            lock.unlock();
        }
    }

    private void clear() {
        try {
            lock.lock();
            this.client = null;
        } finally {
            lock.unlock();
        }
    }

    private void swapInQueue(Client swap) {
        try {
            lock.lock();
            if (client.getCashBoxId() != swap.getCashBoxId()) {
                replace(swap);
                LOG.info("Client № " + client.getClientId() + " passed from cash box   " + (swap.getCashBoxId() + 1) +
                        " to cash box № " + (client.getCashBoxId() + 1));
                LOG.info("Client № " + swap.getClientId() + " passed from " + (client.getCashBoxId() + 1) +
                        " to cash box № " + (swap.getCashBoxId() + 1));
            }
        } finally {
            lock.unlock();
        }
    }

    private void replace(Client swap) {
        try {
            lock.lock();
            int tempId = swap.getClientId();
            swap.setClientId(client.getClientId());
            client.setClientId(tempId);
        } finally {
            lock.unlock();
        }
    }
}
