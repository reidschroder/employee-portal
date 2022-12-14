package com.revature.daos;

import com.revature.controllers.AuthController;
import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class TicketDAO implements TicketDAOInterface {



    //Method for Manager to get All Pending Tickets
    @Override
    public ArrayList<Ticket> getTickets() {

        try(Connection conn = ConnectionUtil.getConnection()){


            String sql = "SELECT * FROM ers_reimbursements WHERE reimbursement_status_id_fk = 1";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Ticket> ticketList = new ArrayList<>();

            while(rs.next()) {

                Ticket t = new Ticket(
                        rs.getInt("ers_reimbursement_id"),
                        rs.getInt("reimbursement_amount"),
                        rs.getString("reimbursement_description"),
                        rs.getInt("user_id_author_fk"),
                        rs.getInt("user_id_resolver_fk"),
                        rs.getInt("reimbursement_type_id_fk"),
                        rs.getInt("reimbursement_status_id_fk")

                );
               ticketList.add(t);
            }

            return ticketList;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    //Method for Associates to get all of their created tickets regardless of status
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
                        rs.getString("reimbursement_description"),
                        rs.getInt("user_id_author_fk"),
                        rs.getInt("user_id_resolver_fk"),
                        rs.getInt("reimbursement_type_id_fk"),
                        rs.getInt("reimbursement_status_id_fk")
                );
                ticketList.add(t);
            }

            return ticketList;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    //Method to create a new reimbursement ticket
    @Override
    public Ticket insertTicket(Ticket tick) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO ers_reimbursements (reimbursement_amount, reimbursement_description, user_id_author_fk, user_id_resolver_fk, reimbursement_type_id_fk, reimbursement_status_id_fk) values (?, ?, ?, null, ?, 1);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, tick.getReimbursement_amount());
            ps.setString(2, tick.getReimbursement_description());
            ps.setInt(3,(Integer)AuthController.ses.getAttribute("user_id"));
            ps.setInt(4, tick.getReimbursement_type_id_fk());


            ps.executeUpdate();

            return tick;


        } catch (SQLException e)

        {
            e.printStackTrace();
        }


        return null;
    }

    //Manager Update Status of Tickets from pending to approved or denied
    @Override
    public boolean updateTicketStatus(int reimbursement_status_id_fk, int ers_reimbursement_id) {

        try (Connection conn = ConnectionUtil.getConnection()) {


            //THIS MIGHT UPDATE ALL REIMBURSEMENT STATUSES. DON"T USE UNTIL WE KNOW THE PROPER INPUT
            String sql = "UPDATE ers_reimbursements SET reimbursement_status_id_fk = ? WHERE ers_reimbursement_id = ?;";


            //Prepared statement so that we can fill appropriate values
            PreparedStatement ps = conn.prepareStatement(sql);

            //using ps.setXYZ we can fill the wildcards (?) in our SQL statement
            ps.setInt(1, reimbursement_status_id_fk);
            ps.setInt(2, ers_reimbursement_id);

            //execute the update
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }


    //Get Ticket Status ID for UpdateTicketStatus (Method Above This One) in order to make tickets that aren't pending immutable
    @Override
    public Ticket getTicketsByStatusId(int ers_reimbursement_id) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM ers_reimbursements WHERE ers_reimbursement_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, ers_reimbursement_id);


            ResultSet rs = ps.executeQuery();



            if(rs.next()) {

                Ticket tbid = new Ticket(
                        rs.getInt("ers_reimbursement_id"),
                        rs.getInt("reimbursement_amount"),
                        rs.getString("reimbursement_description"),
                        rs.getInt("user_id_author_fk"),
                        rs.getInt("user_id_resolver_fk"),
                        rs.getInt("reimbursement_type_id_fk"),
                        rs.getInt("reimbursement_status_id_fk")
                );


                return tbid;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }



}
