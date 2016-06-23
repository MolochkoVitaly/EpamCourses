package by.bsu.internetprovider.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


/**
 * Class ProxyConnection ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ProxyConnection implements Connection {
    /** Field connection  */
    private Connection connection;
    /**
     * Constructor ProxyConnection creates a new ProxyConnection instance.
     *
     * @param connection of type Connection
     */
    ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method createStatement ...
     * @return Statement
     * @throws SQLException when
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Method prepareCall ...
     *
     * @param sql of type String
     * @return CallableStatement
     * @throws SQLException when
     */
    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Method nativeSQL ...
     *
     * @param sql of type String
     * @return String
     * @throws SQLException when
     */
    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Method setAutoCommit sets the autoCommit of this ProxyConnection object.
     *
     *
     *
     * @param autoCommit the autoCommit of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Method getAutoCommit returns the autoCommit of this ProxyConnection object.
     *
     *
     *
     * @return the autoCommit (type boolean) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Method commit ...
     * @throws SQLException when
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Method rollback ...
     * @throws SQLException when
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Method destroy ...
     * @throws SQLException when
     */
    void destroy() throws SQLException {
        connection.close();
    }

    /**
     * Method close ...
     * @throws SQLException when
     */
    @Override
    public void close() throws SQLException {
        ConnectionPool.getInstance().putConnection(this);
    }

    /**
     * Method isClosed returns the closed of this ProxyConnection object.
     *
     *
     *
     * @return the closed (type boolean) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Method getMetaData returns the metaData of this ProxyConnection object.
     *
     *
     *
     * @return the metaData (type DatabaseMetaData) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * Method setReadOnly sets the readOnly of this ProxyConnection object.
     *
     *
     *
     * @param readOnly the readOnly of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * Method isReadOnly returns the readOnly of this ProxyConnection object.
     *
     *
     *
     * @return the readOnly (type boolean) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * Method setCatalog sets the catalog of this ProxyConnection object.
     *
     *
     *
     * @param catalog the catalog of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * Method getCatalog returns the catalog of this ProxyConnection object.
     *
     *
     *
     * @return the catalog (type String) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * Method setTransactionIsolation sets the transactionIsolation of this ProxyConnection object.
     *
     *
     *
     * @param level the transactionIsolation of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * Method getTransactionIsolation returns the transactionIsolation of this ProxyConnection object.
     *
     *
     *
     * @return the transactionIsolation (type int) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * Method getWarnings returns the warnings of this ProxyConnection object.
     *
     *
     *
     * @return the warnings (type SQLWarning) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * Method clearWarnings ...
     * @throws SQLException when
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * Method createStatement ...
     *
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @return Statement
     * @throws SQLException when
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Method prepareCall ...
     *
     * @param sql of type String
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @return CallableStatement
     * @throws SQLException when
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Method getTypeMap returns the typeMap of this ProxyConnection object.
     *
     *
     *
     * @return the typeMap (type Map<String, Class<?>>) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * Method setTypeMap sets the typeMap of this ProxyConnection object.
     *
     *
     *
     * @param map the typeMap of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * Method setHoldability sets the holdability of this ProxyConnection object.
     *
     *
     *
     * @param holdability the holdability of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * Method getHoldability returns the holdability of this ProxyConnection object.
     *
     *
     *
     * @return the holdability (type int) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * Method setSavepoint ...
     * @return Savepoint
     * @throws SQLException when
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * Method setSavepoint ...
     *
     * @param name of type String
     * @return Savepoint
     * @throws SQLException when
     */
    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * Method rollback ...
     *
     * @param savepoint of type Savepoint
     * @throws SQLException when
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * Method releaseSavepoint ...
     *
     * @param savepoint of type Savepoint
     * @throws SQLException when
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * Method createStatement ...
     *
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @param resultSetHoldability of type int
     * @return Statement
     * @throws SQLException when
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @param resultSetHoldability of type int
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Method prepareCall ...
     *
     * @param sql of type String
     * @param resultSetType of type int
     * @param resultSetConcurrency of type int
     * @param resultSetHoldability of type int
     * @return CallableStatement
     * @throws SQLException when
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @param autoGeneratedKeys of type int
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @param columnIndexes of type int[]
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * Method prepareStatement ...
     *
     * @param sql of type String
     * @param columnNames of type String[]
     * @return PreparedStatement
     * @throws SQLException when
     */
    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * Method createClob ...
     * @return Clob
     * @throws SQLException when
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * Method createBlob ...
     * @return Blob
     * @throws SQLException when
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * Method createNClob ...
     * @return NClob
     * @throws SQLException when
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * Method createSQLXML ...
     * @return SQLXML
     * @throws SQLException when
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * Method isValid ...
     *
     * @param timeout of type int
     * @return boolean
     * @throws SQLException when
     */
    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * Method setClientInfo ...
     *
     * @param name of type String
     * @param value of type String
     * @throws SQLClientInfoException when
     */
    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * Method setClientInfo sets the clientInfo of this ProxyConnection object.
     *
     *
     *
     * @param properties the clientInfo of this ProxyConnection object.
     *
     * @throws SQLClientInfoException when
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * Method getClientInfo ...
     *
     * @param name of type String
     * @return String
     * @throws SQLException when
     */
    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * Method getClientInfo returns the clientInfo of this ProxyConnection object.
     *
     *
     *
     * @return the clientInfo (type Properties) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * Method createArrayOf ...
     *
     * @param typeName of type String
     * @param elements of type Object[]
     * @return Array
     * @throws SQLException when
     */
    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * Method createStruct ...
     *
     * @param typeName of type String
     * @param attributes of type Object[]
     * @return Struct
     * @throws SQLException when
     */
    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * Method setSchema sets the schema of this ProxyConnection object.
     *
     *
     *
     * @param schema the schema of this ProxyConnection object.
     *
     * @throws SQLException when
     */
    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * Method getSchema returns the schema of this ProxyConnection object.
     *
     *
     *
     * @return the schema (type String) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * Method abort ...
     *
     * @param executor of type Executor
     * @throws SQLException when
     */
    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * Method setNetworkTimeout ...
     *
     * @param executor of type Executor
     * @param milliseconds of type int
     * @throws SQLException when
     */
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * Method getNetworkTimeout returns the networkTimeout of this ProxyConnection object.
     *
     *
     *
     * @return the networkTimeout (type int) of this ProxyConnection object.
     * @throws SQLException when
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     * Method unwrap ...
     *
     * @param iface of type Class<T>
     * @return T
     * @throws SQLException when
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * Method isWrapperFor ...
     *
     * @param iface of type Class<?>
     * @return boolean
     * @throws SQLException when
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}
