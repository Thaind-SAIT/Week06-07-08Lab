/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author 808278
 */
public class UserDB {
    
    private Connection connection;
    private ConnectionPool pool;
    
    public UserDB() {
        pool = ConnectionPool.getInstance();
        this.connection = pool.getConnection();
    }
    
    public int insert(User user) throws NotesDBException {
        int rows = 0;
        try {
            String preparedQuery = 
                "INSERT INTO users "
                + "(username, password, firstname, lastname, email) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }

    public int update(User user) throws NotesDBException {
        int rows = 0;
        try {
            String preparedQuery = 
                "UPDATE users SET"
                + " password = ?,"
                + " firstname = ?,"
                + " lastname = ?,"
                + " email = ?"
                + " WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }

    public List<User> getAll() throws NotesDBException {
        List<User> users = new ArrayList<User>();
        
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            while ( rs.next() ) {
                users.add( new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email") ) );
            }
            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User getUser(String username) throws NotesDBException {
        User user = null;
        
        try {
            String preparedSQL = "SELECT * " 
                   + "FROM users WHERE username = ?";
            PreparedStatement ps = this.connection.prepareStatement(preparedSQL);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                user = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email") );
            }
            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public int delete(String username) throws NotesDBException {
        int rows = 0;
        try {
            String preparedQuery = 
                "DELETE FROM users "
                + "WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, username);
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
    
    public void closeConnection() {
        this.pool.freeConnection(connection);
    }
}
