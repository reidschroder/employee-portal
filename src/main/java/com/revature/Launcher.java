package com.revature;

import com.revature.controllers.*;
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

        //Get All Pending Tickets -- MANAGER VIEW
        app.get("/tickets", tc.getTicketsHandler);

        //Get Tickets By User ID -- ASSOCIATE VIEW
        app.get("/tickets/userid", tc.getTicketsByAuthorIdHandler);

        // LOGIN
        app.post("/login", ac.loginHandler);

        // REGISTER
        app.post("/register", uc.userHandler);

        //Update Reimbursement Status -- MANAGER VIEW
        app.patch("/update/{ers_reimbursement_id}", tc.updateTicketStatusHandler);

        //Create New Reimbursement Request -- ASSOCIATE VIEW
        app.post("/tickets/create", tc.createTicketHandler);
    }

}
