package by.bsu.internetprovider.pool;


import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class ConnectionPool ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ConnectionPool {
    /** Field LOG  */
    public final static Logger LOG = LogManager.getLogger(ConnectionPool.class);

    /** Field instance  */
    private static ConnectionPool instance;

    /** Field isCreated  */
    private static AtomicBoolean isCreated = new AtomicBoolean();

    /** Field lock  */
    private static Lock lock = new ReentrantLock();

    /** Field connections  */
    private BlockingQueue<ProxyConnection> connections;

    /** Field url  */
    private String url;

    /** Field login  */
    private String login;

    /** Field password  */
    private String password;

    /**
     * Constructor ConnectionPool creates a new ConnectionPool instance.
     *
     * @param url of type String
     * @param login of type String
     * @param password of type String
     * @param initConnectionCount of type int
     */
    private ConnectionPool(String url, String login, String password, int initConnectionCount) {
        this.connections = new LinkedBlockingQueue<>();
        this.url = url;
        this.login = login;
        this.password = password;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            for (int i = 0; i < initConnectionCount; i++) {
                connections.add(getConnection());
            }
        }
        catch (ConnectionPoolException | SQLException e ) {
            LOG.fatal("Error in initializing the pool ");
            throw new RuntimeException("Error in initialization");
        }
    }

    /**
     * Method getInstance returns the instance of this ConnectionPool object.
     *
     *
     *
     * @return the instance (type ConnectionPool) of this ConnectionPool object.
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (!isCreated.get()) {
                    String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_URL);
                    String login = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN);
                    String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PASSWORD);
                    int count = Integer.parseInt(ConfigurationManager.getInstance().getProperty(ConfigurationManager.POOL_SIZE));
                    instance = new ConnectionPool(url, login, password, count);
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Method getConnection returns the connection of this ConnectionPool object.
     *
     *
     *
     * @return the connection (type ProxyConnection) of this ConnectionPool object.
     * @throws ConnectionPoolException when
     */
    private ProxyConnection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, login, password);
        }
        catch (SQLException e) {
            throw new ConnectionPoolException("Error! Creating connection has been failed!" , e);
        }
        return new ProxyConnection(connection);
    }

    /**
     * Method takeConnection ...
     * @return ProxyConnection
     * @throws ConnectionPoolException when
     */
    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = connections.take();
        }
        catch (InterruptedException e) {
            throw new ConnectionPoolException("Error! Can't take connection!", e);
        }
        return connection;
    }

    /**
     * Method putConnection ...
     *
     * @param proxyConnection of type ProxyConnection
     */
    public void putConnection(ProxyConnection proxyConnection) {
        connections.add(proxyConnection);
    }

    /**
     * Method size ...
     * @return int
     */
    public int size() {
        return connections.size();
    }

    /**
     * Method destroyConnections ...
     */
    public void destroyConnections() {
        int counter = 0;
        for (ProxyConnection pc : connections) {
            try {
                pc.destroy();
                counter++;
            }
            catch (SQLException ex) {
                LOG.error("SQLException", ex);
            }
        }
        LOG.info("Closed " + counter + " connections");
    }
}
