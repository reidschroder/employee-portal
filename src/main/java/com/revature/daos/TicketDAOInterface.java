package com.revature.daos;

import com.revature.models.Ticket;

import java.util.ArrayList;

public interface TicketDAOInterface {

    ArrayList<Ticket> getTickets();

    ArrayList<Ticket> getTicketsById();


    Ticket insertTicket(Ticket tick);

}
