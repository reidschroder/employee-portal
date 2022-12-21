package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.AuthDAO;

import javax.servlet.http.HttpSession;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import io.javalin.http.Handler;

public class AuthController {

    AuthDAO aDAO = new AuthDAO();

    UserDAO uDAO = new UserDAO();

    public static HttpSession ses;

    public Handler loginHandler = (ctx) -> {

        String body = ctx.body();

        Gson gson = new Gson();

        LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);

        User loggedInUser = aDAO.login(lDTO.getErs_username(), lDTO.getErs_password());


        if (loggedInUser != null) {
            ses = ctx.req.getSession();

            ses.setAttribute("user_role_id", loggedInUser.getRole().getUser_role_id());
            ses.setAttribute("user_id", loggedInUser.getUser_id());

            String userJSON = gson.toJson(loggedInUser);

            ctx.result(userJSON);
            ctx.status(202);
        } else {
            ctx.status(401);
        }
    };

//    public Handler registerHandler = (ctx) -> {
//
//        String body = ctx.body();
//
//        Gson gson = new Gson();
//
//        //LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
//        User use = gson.fromJson(body, User.class);
//
//        if(uDAO.registerHandler(use) != null){
//            ctx.status(201);
//            ctx.result(body);
//        } else {
//            ctx.status(406);
//            ctx.result("Employee username is already taken. Please create valid username.")
//        }
//
//    };
}
