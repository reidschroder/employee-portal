package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    UserDAO uDAO = new UserDAO();


    //Handler to Get All Users
    //NOT USED IN PROJECT PRESENTATION
    public Handler getUsersHandler = (ctx) -> {

        ArrayList<User> users = uDAO.getUsers();

        Gson gson = new Gson();

        String JSONUsers = gson.toJson(users);

        ctx.result(JSONUsers);

        ctx.status(202);
    };

    public Handler userHandler = (ctx) -> {

        String body = ctx.body();

        Gson gson = new Gson();


        User use = gson.fromJson(body, User.class);

        use = uDAO.insertUser(use);


        if(use != null){
            ctx.status(201);
            ctx.result(body);
        } else {
            ctx.status(401);
            ctx.result("Employee username is already taken. Please create unique username.");
        }

    };


}
