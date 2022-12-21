package com.revature.daos;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//public class TicketStatusDAO implements TicketStatusDAOInterface {


    //Manager Update Status of Tickets from pending to approved or denied
//    @Override
//    public boolean updateTicketStatus(int reimbursement_status_id, String reimbursement_status) {
//
//        try (Connection conn = ConnectionUtil.getConnection()) {
//
//            //create our SQL String (to be filled with values from the method arguments)
//
//            //THIS MIGHT UPDATE ALL REIMBURSEMENT STATUSES. DON"T USE UNTIL WE KNOW THE PROPER INPUT
//            String sql = "UPDATE ers_reimbursement_status SET reimbursement_status_id = ? WHERE reimbursement_status = ?;";
//
//
//            //Prepared statement so that we can fill appropriate values
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            //using ps.setXYZ we can fill the wildcards (?) in our SQL statement
//            ps.setInt(1, reimbursement_status_id);
//            ps.setString(2, reimbursement_status);
//
//            //execute the update
//            ps.executeUpdate();
//
//            return true;
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
