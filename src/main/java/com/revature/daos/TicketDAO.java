package com.revature.daos;

import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TicketDAO implements TicketDAOInterface {


    @Override
    public ArrayList<Ticket> getTickets() {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM ers_reimbursements";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Ticket> ticketList = new ArrayList<>();

            while(rs.next()) {

                Ticket t = new Ticket(
                        rs.getInt("ers_reimbursement_id"),
                        rs.getInt("reimbursement_amount"),
                        rs.getString("reimbursement_description")

                );
               ticketList.add(t);
            }

            return ticketList;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketsById() {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM ers_reimbursements WHERE user_id_author_fk = ?";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Ticket> ticketList = new ArrayList<>();

            while(rs.next()) {

                Ticket t = new Ticket(
                        rs.getInt("ers_reimbursement_id"),
                        rs.getInt("reimbursement_amount"),
                        rs.getString("reimbursement_description")

                );
                ticketList.add(t);
            }

            return ticketList;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Ticket insertTicket(Ticket tick) {
        return null;
    }
}
