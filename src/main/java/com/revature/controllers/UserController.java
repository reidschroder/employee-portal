package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    UserDAO uDAO = new UserDAO();


    //Handler to Get All Users
    public Handler getUsersHandler = (ctx) -> {
         //if(AuthController.ses != null){
        ArrayList<User> users = uDAO.getUsers();

        Gson gson = new Gson();

        String JSONUsers = gson.toJson(users);

        ctx.result(JSONUsers);

        ctx.status(202);
    //} else {
        //ctx.result("YOU MUST BE A MANAGER TO DO THIS");
        //ctx.status(401);
    };


}
