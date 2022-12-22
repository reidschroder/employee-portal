package com.revature.daos;

import com.revature.models.Ticket;

import java.util.ArrayList;

public interface TicketDAOInterface {

    ArrayList<Ticket> getTickets();

    ArrayList<Ticket> getTicketsById(int user_id);

    boolean updateTicketStatus(int reimbursement_status_id_fk, int ers_reimbursement_id);


    Ticket insertTicket(Ticket tick);


    //Create a restriction so managers cannot change Reimbursement ID's After they have been changed
    Ticket getTicketsByStatusId(int ers_reimbursement_id);

}
