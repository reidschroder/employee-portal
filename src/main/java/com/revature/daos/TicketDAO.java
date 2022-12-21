package com.revature.daos;

import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
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
    public ArrayList<Ticket> getTicketsById(int user_id) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM ers_reimbursements WHERE user_id_author_fk = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user_id);


            ResultSet rs = ps.executeQuery();

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
