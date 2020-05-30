package com.lch.dbpool.adapter;


import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.*;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;

import java.io.Serializable;
import java.sql.*;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 这个类是为了当MyConn的适配器
 * 因为我只想用到Connection中close()方法来改变conn的状态和PreparedStatement()
 * Connection这个接口中其他的方法又不想用
 * 适配器模式
 */
public class ConnAdapter implements JdbcConnection, Session.SessionEventListener, Serializable {


    public void handleNormalClose() {

    }

    public void handleReconnect() {

    }

    public void handleCleanup(Throwable throwable) {

    }

    public JdbcPropertySet getPropertySet() {
        return null;
    }

    public void createNewIO(boolean b) {

    }

    public long getId() {
        return 0;
    }

    public Properties getProperties() {
        return null;
    }

    public Object getConnectionMutex() {
        return null;
    }

    public Session getSession() {
        return null;
    }

    public String getURL() {
        return null;
    }

    public String getUser() {
        return null;
    }

    public ExceptionInterceptor getExceptionInterceptor() {
        return null;
    }

    public void checkClosed() {

    }

    public void normalClose() {

    }

    public void cleanup(Throwable throwable) {

    }

    public void changeUser(String s, String s1) throws SQLException {

    }

    public void clearHasTriedMaster() {

    }

    public PreparedStatement clientPrepareStatement(String s) throws SQLException {
        return null;
    }

    public PreparedStatement clientPrepareStatement(String s, int i) throws SQLException {
        return null;
    }

    public PreparedStatement clientPrepareStatement(String s, int i, int i1) throws SQLException {
        return null;
    }

    public PreparedStatement clientPrepareStatement(String s, int[] ints) throws SQLException {
        return null;
    }

    public PreparedStatement clientPrepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    public PreparedStatement clientPrepareStatement(String s, String[] strings) throws SQLException {
        return null;
    }

    public int getActiveStatementCount() {
        return 0;
    }

    public long getIdleFor() {
        return 0;
    }

    public String getStatementComment() {
        return null;
    }

    public boolean hasTriedMaster() {
        return false;
    }

    public boolean isInGlobalTx() {
        return false;
    }

    public void setInGlobalTx(boolean b) {

    }

    public boolean isMasterConnection() {
        return false;
    }

    public boolean isSameResource(JdbcConnection jdbcConnection) {
        return false;
    }

    public boolean lowerCaseTableNames() {
        return false;
    }

    public void ping() throws SQLException {

    }

    public void resetServerState() throws SQLException {

    }

    public PreparedStatement serverPrepareStatement(String s) throws SQLException {
        return null;
    }

    public PreparedStatement serverPrepareStatement(String s, int i) throws SQLException {
        return null;
    }

    public PreparedStatement serverPrepareStatement(String s, int i, int i1) throws SQLException {
        return null;
    }

    public PreparedStatement serverPrepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return null;
    }

    public PreparedStatement serverPrepareStatement(String s, int[] ints) throws SQLException {
        return null;
    }

    public PreparedStatement serverPrepareStatement(String s, String[] strings) throws SQLException {
        return null;
    }

    public void setFailedOver(boolean b) {

    }

    public void setStatementComment(String s) {

    }

    public void shutdownServer() throws SQLException {

    }

    public int getAutoIncrementIncrement() {
        return 0;
    }

    public boolean hasSameProperties(JdbcConnection jdbcConnection) {
        return false;
    }

    public String getHost() {
        return null;
    }

    public String getHostPortPair() {
        return null;
    }

    public void setProxy(JdbcConnection jdbcConnection) {

    }

    public boolean isServerLocal() throws SQLException {
        return false;
    }

    public int getSessionMaxRows() {
        return 0;
    }

    public void setSessionMaxRows(int i) throws SQLException {

    }

    public void abortInternal() throws SQLException {

    }

    public boolean isProxySet() {
        return false;
    }

    public CachedResultSetMetaData getCachedMetaData(String s) {
        return null;
    }

    public String getCharacterSetMetadata() {
        return null;
    }

    public Statement getMetadataSafeStatement() throws SQLException {
        return null;
    }

    public ServerVersion getServerVersion() {
        return null;
    }

    public List<QueryInterceptor> getQueryInterceptorsInstances() {
        return null;
    }

    public void initializeResultsMetadataFromCache(String s, CachedResultSetMetaData cachedResultSetMetaData, ResultSetInternalMethods resultSetInternalMethods) throws SQLException {

    }

    public void initializeSafeQueryInterceptors() throws SQLException {

    }

    public boolean isReadOnly(boolean b) throws SQLException {
        return false;
    }

    public void pingInternal(boolean b, int i) throws SQLException {

    }

    public void realClose(boolean b, boolean b1, boolean b2, Throwable throwable) throws SQLException {

    }

    public void recachePreparedStatement(JdbcPreparedStatement jdbcPreparedStatement) throws SQLException {

    }

    public void decachePreparedStatement(JdbcPreparedStatement jdbcPreparedStatement) throws SQLException {

    }

    public void registerStatement(JdbcStatement jdbcStatement) {

    }

    public void setReadOnlyInternal(boolean b) throws SQLException {

    }

    public boolean storesLowerCaseTableName() {
        return false;
    }

    public void throwConnectionClosedException() throws SQLException {

    }

    public void unregisterStatement(JdbcStatement jdbcStatement) {

    }

    public void unSafeQueryInterceptors() throws SQLException {

    }

    public JdbcConnection getMultiHostSafeProxy() {
        return null;
    }

    public JdbcConnection getMultiHostParentProxy() {
        return null;
    }

    public JdbcConnection getActiveMySQLConnection() {
        return null;
    }

    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        return null;
    }

    public void setDatabase(String s) throws SQLException {

    }

    public String getDatabase() throws SQLException {
        return null;
    }

    public void transactionBegun() {

    }

    public void transactionCompleted() {

    }

    public Statement createStatement() throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return null;
    }

    public String nativeSQL(String sql) throws SQLException {
        return null;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {

    }

    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    public void commit() throws SQLException {

    }

    public void rollback() throws SQLException {

    }

    public void close() throws SQLException {

    }

    public boolean isClosed() throws SQLException {
        return false;
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    public void setReadOnly(boolean readOnly) throws SQLException {

    }

    public boolean isReadOnly() throws SQLException {
        return false;
    }

    public void setCatalog(String catalog) throws SQLException {

    }

    public String getCatalog() throws SQLException {
        return null;
    }

    public void setTransactionIsolation(int level) throws SQLException {

    }

    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public void clearWarnings() throws SQLException {

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

    }

    public void setHoldability(int holdability) throws SQLException {

    }

    public int getHoldability() throws SQLException {
        return 0;
    }

    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    public void rollback(Savepoint savepoint) throws SQLException {

    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return null;
    }

    public Clob createClob() throws SQLException {
        return null;
    }

    public Blob createBlob() throws SQLException {
        return null;
    }

    public NClob createNClob() throws SQLException {
        return null;
    }

    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    public boolean isValid(int timeout) throws SQLException {
        return false;
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {

    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        return null;
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    public void setSchema(String schema) throws SQLException {

    }

    public String getSchema() throws SQLException {
        return null;
    }

    public void abort(Executor executor) throws SQLException {

    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

    }

    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
