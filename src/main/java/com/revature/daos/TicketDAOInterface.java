package com.revature.daos;

import com.revature.models.Ticket;

import java.util.ArrayList;

public interface TicketDAOInterface {

    ArrayList<Ticket> getTickets();


    Ticket insertTicket(Ticket tick);

}
