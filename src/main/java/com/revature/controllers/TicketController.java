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


            } else {
                ctx.status(401);
                ctx.result("Only managers can view all pending tickets. Associates must use the Associate portal to view their ticket history.");
            }
        } else {
            ctx.status(401);
            ctx.result("Please log in via manager portal to use this function");
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

            } else {
                ctx.status(401);
                ctx.result("Only associates can view their own tickets. If you are an associate, please log in. Managers must use the manager portal to view all pending tickets.");
            }
        } else {
            ctx.status(401);
            ctx.result("Please log in via associate portal to use this function");
        }
    };


    //MANAGER VIEW -- Update Reimbursement Status From Pending To Accepted Or Denied
    public Handler updateTicketStatusHandler = (ctx) -> {
        if (AuthController.ses != null) {

            if ((Integer)AuthController.ses.getAttribute("user_role_id") == 1) {

                int reimbursement_status_id_fk = Integer.parseInt(ctx.body());
                int ers_reimbursement_id = Integer.parseInt(ctx.pathParam("ers_reimbursement_id"));


                //new code. delete int & if(StatusID != 1) if this doesn't end up working
                int StatusId = tDAO.getTicketsByStatusId(ers_reimbursement_id).getReimbursement_status_id_fk();

                if(StatusId != 1) {
                    ctx.status(406);
                    ctx.result("Previously Approved or Denied tickets cannot be changed!");
                }
                else {
                    boolean updated = tDAO.updateTicketStatus(reimbursement_status_id_fk, ers_reimbursement_id);
                    if (updated) {
                    ctx.status(201);
                    ctx.result("Reimbursement Updated");
                    }
                    else {
                    ctx.status(401);
                    ctx.result("Reimbursement Update Failed. You must update the foreign id to 2-Approved OR 3-Denied");
                    }
                }
            }
            else {
                ctx.status(401);
                ctx.result("You have to be a manager to edit reimbursement tickets!");
            }
        }
        else {
            ctx.status(401);
            ctx.result("Please log in via manager portal to use this function");
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
                } else {
                    ctx.status(400);
                    ctx.result("Please enter all required fields");
                    }

            } else {
                ctx.status(401);
                ctx.result("Only Associates can create a new Reimbursement Request. If you are an Associate, please enter valid fields. You must submit an answer for each field");
                }
        } else {
            ctx.status(401);
            ctx.result("Please log in via associate portal to use this function");
            }


    };
}
