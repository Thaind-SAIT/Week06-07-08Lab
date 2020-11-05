/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.UserDB;
import java.util.List;
import models.User;

/**
 *
 * @author 808278
 */
public class UserService {
    private UserDB userDB;
    
    public UserService() {
    }
    
    public User get(String username) throws Exception {
        this.userDB = new UserDB();
        try {
            return this.userDB.getUser(username);
        } finally {
            this.userDB.closeConnection();
        }
        
    }

    public List<User> getAll() throws Exception {
        this.userDB = new UserDB();
        try {
            return this.userDB.getAll();
        } finally {
            this.userDB.closeConnection();
        }
    }

    public int update(String username, String password, String firstname, String lastname, String email) throws Exception {
        this.userDB = new UserDB();
        try {
            return this.userDB.update(new User(username, password, firstname, lastname, email));
        } finally {
            this.userDB.closeConnection();
        }
    }

    public int delete(String username) throws Exception {
        this.userDB = new UserDB();
        try {
            return this.userDB.delete(username);
        } finally {
            this.userDB.closeConnection();
        }
    }

    public int insert(String username, String password, String firstname, String lastname, String email) throws Exception {
        this.userDB = new UserDB();
        try {
            return this.userDB.insert(new User(username, password, firstname, lastname, email));
        } finally {
            this.userDB.closeConnection();
        }
    }
}
