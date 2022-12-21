package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.RoleController;
import com.revature.controllers.TicketController;
import com.revature.controllers.UserController;
import com.revature.daos.UserDAO;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("CONNECTION SUCCESSFUL!");
        } catch(SQLException e) {
            System.out.println("connection failed");

            e.printStackTrace();
        }

        //Create Javalin object
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                }
        ).start(3000);

        //CREATE ENDPOINT HANDLERS HERE =====================================================

        UserController uc = new UserController();
        RoleController rc = new RoleController();

        TicketController tc = new TicketController();

        AuthController ac = new AuthController();



        app.get("/users", uc.getUsersHandler);
        //app.get("/roles", rc.get);

        app.get("/tickets", tc.getTicketsHandler);


        app.post("/login", ac.loginHandler);

        app.post("/register", uc.userHandler);

//        UserDAO uDAO = new UserDAO();
//
//        System.out.println(uDAO.getUsers());


    }

}
