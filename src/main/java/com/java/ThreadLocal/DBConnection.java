package com.java.ThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Thread-safe database connection manager.
 *
 * Provides a per-thread {@link Connection} instance using {@link ThreadLocal}.
 */
public class DBConnection {

    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    private static String DB_URL;

    // ThreadLocal holding one Connection per thread
    private static final ThreadLocal<Connection> connHolder = ThreadLocal.withInitial(() -> {
        try {
            logger.info("Creating new DB connection for thread: " + Thread.currentThread().getName());
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            logger.severe("Failed to create DB connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    });

    /**
     * Initializes the DB URL.
     * Must be called once before any calls to getConnection().
     *
     * @param dbUrl the database URL
     */
    public static void initialize(String dbUrl) {
        DB_URL = dbUrl;
        logger.info("DBConnection initialized with URL: " + dbUrl);
    }

    /**
     * Returns the Connection associated with the current thread.
     *
     * @return the current thread's DB Connection
     * @Throw IllegalStateException if DB_URL is null which means the caller forgot to initialize first.
     */
    public static Connection getConnection() {
        if (DB_URL == null) {
            logger.warning("Invalid Database url , call initialize() first");
            throw new IllegalStateException("DBConnection is not initialized. Call initialize() first.");
        }
        return connHolder.get();
    }

    /**
     * Closes and removes the current thread's connection.
     */
    public static void closeConnection() {
        Connection conn = connHolder.get();
        if (conn != null) {
            try {
                conn.close();
                logger.info("Closed DB connection for thread: " + Thread.currentThread().getName());
            } catch (SQLException e) {
                logger.severe("Error closing connection: " + e.getMessage());
            } finally {
                connHolder.remove();
            }
        }
    }
}
