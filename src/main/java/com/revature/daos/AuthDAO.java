package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    //LOGIN==========================================================
    public User login(String ers_username, String ers_password){
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ers_username);
            ps.setString(2, ers_password);

            ResultSet rs = ps.executeQuery();


            if(rs.next()){

                User u = new User(

                        rs.getInt("user_id"),
                        rs.getString("ers_username"),
                        rs.getString("ers_password"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        null
                );

                int roleFk = rs.getInt("user_role_id_fk");

                RoleDAO rDAO = new RoleDAO();

                Role r = rDAO.getRoleById(roleFk);

                u.setRole(r);

                return u;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//    //ADD INSERT USER METHOD HERE FOR REGISTER=====================================
//    public User register(User use){
//        try(Connection conn = ConnectionUtil.getConnection()){
//
//            String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_role_id_fk) values (?, ?, ?, ?, 2);";
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            ps.setString(1, use.getErs_username());
//            ps.setString(2, use.getErs_password());
//            ps.setString(1, use.getErs_username());
//            ps.setString(2, use.getUser_last_name());
//
//            ps.executeUpdate();
//
//            return use;
//
//
//
//
//        } catch (SQLException e)
//
//    {
//        e.printStackTrace();
//    }
//        return null;
//    }
}
