package com.revature.daos;

import com.revature.models.User;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {

    @Override
    public ArrayList<User> getUsers() {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_users;";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<User> userList = new ArrayList<>();

            while(rs.next()){

                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("ers_username"),
                        rs.getString("ers_password"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        null
                );

                int roleFK = rs.getInt("user_role_id_fk");

                RoleDAO rDAO = new RoleDAO();
                Role r = rDAO.getRoleById(roleFK);

                u.setRole(r);
                u.setUser_role_id_fk(roleFK);

                userList.add(u);
            }

            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return null;
    }

    @Override
    public User insertUser(User use) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_role_id_fk) values (?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, use.getErs_username());
            ps.setString(2, use.getErs_password());
            ps.setString(3, use.getUser_first_name());
            ps.setString(4, use.getUser_last_name());
            ps.setInt(5, use.getUser_role_id_fk());

            ps.executeUpdate();

            return use;


        } catch(SQLException e) {
            e.printStackTrace();
        }



        return null;
    }
}
