/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  808278
 * Created: Oct 27, 2020
 */

DROP DATABASE if exist MyDB;
CREATE DATABASE MyDB;

USE MyDB;

CREATE TABLE users (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(40),
    CONSTRAINT PK_username PRIMARY KEY (username)
);

INSERT INTO users(username, password) VALUES ("admin", "password");
COMMIT;