import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * The Class PoolTest.
 */
public class ConnectionPoolTest {

    /** The Constant POOL_SIZE. */
    private static final int POOL_SIZE = 10;

    /**
     * Pool init.
     */
    @Test
    public void poolInit() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Assert.assertTrue(pool.size() == POOL_SIZE);
    }

    /**
     * Taking connection test.
     */
    @Test
    public void takingConnectionTest() {
        try {
            ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
            Assert.assertTrue(ConnectionPool.getInstance().size() == POOL_SIZE - 1);
            connection.close();
            Assert.assertTrue(ConnectionPool.getInstance().size() == POOL_SIZE);
        }
        catch (ConnectionPoolException ex) {
            Assert.fail("ConnectionPool Exception!!!");
        }
        catch (SQLException ex) {
            Assert.fail("SQLException!!!");
        }
    }
}
