package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.TicketDAO;
import com.revature.models.Ticket;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class TicketController {

    TicketDAO tDAO = new TicketDAO();

    //Manager View ONLY. Get All Pending Tickets
    public Handler getTicketsHandler = (ctx) -> {


        if (AuthController.ses != null) {

            if ((Integer) AuthController.ses.getAttribute("user_role_id") == 1) {


                ArrayList<Ticket> tickets = tDAO.getTickets();

                Gson gson = new Gson();

                String JSONTickets = gson.toJson(tickets);

                ctx.result(JSONTickets);

                ctx.status(202);


            }
        } else {
            ctx.status(401);
            ctx.result("Only managers can view all pending tickets. If you are a manager, please log in. Associates must use the Associate portal to view their ticket history.");
        }

    };


    //ASSOCIATE VIEW -- Get All Tickets For Associate by their login id
    public Handler getTicketsByAuthorIdHandler = (ctx) -> {

        //This method is meant to only be accessed by associates
        if (AuthController.ses != null) {

            if ((Integer)AuthController.ses.getAttribute("user_role_id") == 2) {


            int user_id = (Integer) AuthController.ses.getAttribute("user_id");

            ArrayList<Ticket> tickets = tDAO.getTicketsById(user_id);

            Gson gson = new Gson();

            String JSONTickets = gson.toJson(tickets);

            ctx.result(JSONTickets);

            ctx.status(202);

        }
        }else {
            ctx.status(401);
            ctx.result("Only associates can view their own tickets. If you are an associate, please log in. Managers must use the manager portal to view all pending tickets.");
        }
    };


    //MANAGER VIEW -- Update Reimbursement Status From Pending To Accepted Or Denied
    public Handler updateTicketStatusHandler = (ctx) -> {
        if (AuthController.ses != null) {

            if ((Integer)AuthController.ses.getAttribute("user_role_id") == 1) {

                int reimbursement_status_id_fk = Integer.parseInt(ctx.body());

                int ers_reimbursement_id = Integer.parseInt(ctx.pathParam("ers_reimbursement_id"));

                boolean updated = tDAO.updateTicketStatus(reimbursement_status_id_fk, ers_reimbursement_id);

                if (updated) {
                    ctx.status(201);
                    ctx.result("Reimbursement Updated");
                }
            }
        }else {
            ctx.status(401);
            ctx.result("You must be a Manager OR You must update the foreign id to 2-Approved OR 3-Denied");
        }
    };


    //ASSOCIATE VIEW -- Create Reimbursement Request
    public Handler createTicketHandler = (ctx) -> {

        if (AuthController.ses != null) {

            if ((Integer) AuthController.ses.getAttribute("user_role_id") == 2) {

                String body = ctx.body();

                Gson gson = new Gson();

                Ticket tick = gson.fromJson(body, Ticket.class);

                tick = tDAO.insertTicket(tick);

                if(tick != null){
                    ctx.status(201);
                    ctx.result(body);
                }



            }
        }else {
            ctx.status(401);
            ctx.result("Only Associates can create a new Reimbursement Request. If you are an Associate, please enter valid fields. You must submit an answer for each field");
        }

    };
}
