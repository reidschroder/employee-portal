package com.revature.daos;


import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    ArrayList<User> getUsers();

    // Insert Users. MANAGER ONLY ?????????????????
    User insertUser(User use);

}
