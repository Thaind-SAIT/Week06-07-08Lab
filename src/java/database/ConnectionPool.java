/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author 808278
 */
public class ConnectionPool {
    /**
     * @param pool ConnectionPool set to null
     * @param dataSource DataSource set to null
     */
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    /**
     * Creates a connection 
     */
    private ConnectionPool() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/mydb");
        } catch (NamingException e) {
            System.out.println(e);
        }

    }
    
    /**
     * If the connection pool is null(default) it will create the connection
     * @return the connection pool
     */
    public static synchronized ConnectionPool getInstance() {
        if(pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    /**
     * Gets the connection, catches SQLException
     * @return returns the connection
     */
    public Connection getConnection() {
        try{
            return dataSource.getConnection();
           
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Closes the connection so more users can connect at once.
     * @param c connection that is passed in
     */
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
}
