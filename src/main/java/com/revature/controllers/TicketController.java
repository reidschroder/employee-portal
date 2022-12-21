package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.TicketDAO;
import com.revature.models.Ticket;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class TicketController {

    TicketDAO tDAO = new TicketDAO();

    //Handler to get all tickets

    //Manager View ONLY
    public Handler getTicketsHandler = (ctx) -> {
        //ArrayList<Ticket> tickets = tDAO.getTickets();

        if (AuthController.ses != null) {

            if ((Integer) AuthController.ses.getAttribute("user_role_id") == 1) {

                int user_id = (Integer) AuthController.ses.getAttribute("user_id");


                //We need an ArrayList of Employees, courtesy of our EmployeeDAO
                ArrayList<Ticket> tickets = tDAO.getTickets();

                Gson gson = new Gson();

                String JSONTickets = gson.toJson(tickets);

                ctx.result(JSONTickets);

                ctx.status(202);


            } else {
                ctx.status(401);
                ctx.result("Only managers can view all tickets. If you are a manager, please log in. Associates must use the Associate portal to view their ticket history.");
            }
        }
//        Gson gson = new Gson();
//
//        String JSONTickets = gson.toJson(tickets);
//
//        ctx.result(JSONTickets);
//
//        ctx.status(202);
    };


    public Handler getTicketsByAuthorIdHandler = (ctx) -> {
        //ArrayList<Ticket> tickets = tDAO.getTicketsById(user_id);


        //This method is meant to only be accessed by associates
        // Figure out how to make this non-accessible for Managers
        if (AuthController.ses != null) {

            if ((Integer)AuthController.ses.getAttribute("user_role_id") == 2) {


            int user_id = (Integer) AuthController.ses.getAttribute("user_id");
            //We need an ArrayList of Employees, courtesy of our EmployeeDAO
            ArrayList<Ticket> tickets = tDAO.getTicketsById(user_id);

            Gson gson = new Gson();

            String JSONTickets = gson.toJson(tickets);

            ctx.result(JSONTickets);

            ctx.status(202);


        } else {
            ctx.status(401);
            ctx.result("Only associates can view their own tickets. If you are an associate, please log in. Managers must use the manager portal to view all pending tickets.");
        }}
        ;
    };
}
