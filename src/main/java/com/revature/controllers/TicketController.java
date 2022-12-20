package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.TicketDAO;
import com.revature.models.Ticket;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class TicketController {

    TicketDAO tDAO = new TicketDAO();

    //Handler to get all tickets

    public Handler getTicketsHandler = (ctx) -> {
        ArrayList<Ticket> tickets = tDAO.getTickets();

        Gson gson = new Gson();

        String JSONTickets = gson.toJson(tickets);

        ctx.result(JSONTickets);

        ctx.status(202);
    };
}
