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

//        TicketStatusController tsc = new TicketStatusController();

        AuthController ac = new AuthController();



        app.get("/users", uc.getUsersHandler);
        //app.get("/roles", rc.get);



        app.get("/tickets", tc.getTicketsHandler);


        //get tickets by user ID
        app.get("/tickets/userid", tc.getTicketsByAuthorIdHandler);


        app.post("/login", ac.loginHandler);

        app.post("/register", uc.userHandler);

         app.patch("/update/{ers_reimbursement_id}", tc.updateTicketStatusHandler);

         app.post("/tickets/create", tc.createTicketHandler);
//        UserDAO uDAO = new UserDAO();
//
//        System.out.println(uDAO.getUsers());


    }

}
