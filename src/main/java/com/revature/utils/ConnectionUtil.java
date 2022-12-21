package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//manage and establish our database connection
public class ConnectionUtil {


    //This method will eventually return an object of type Connection, which we'll use to connect to our databse
    public static Connection getConnection() throws SQLException {

        //For compatibility with other technologies/frameworks, we'll need to register our PostgreSQL driver
        //This process makes the application aware of what Driver class we're using
        try {
            Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //This tells in the console us what went wrong
            System.out.println("problem occurred locating driver");
        }


        //Use our database credentials to establish a database connection

        String url = System.getenv("postgresurl");
        String username = System.getenv("postgresusername");
        String password = System.getenv("postgrespassword");
        /*
        url - location of your database/schema
        username - postgres username
        password - postgres password
         */

        //This return statement is what returns out actual database Connection object
        return DriverManager.getConnection(url, username, password);

    }

}