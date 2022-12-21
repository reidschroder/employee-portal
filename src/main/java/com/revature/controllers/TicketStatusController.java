package com.revature.controllers;

import com.google.gson.Gson;
//import com.revature.daos.TicketStatusDAO;
import com.revature.models.TicketStatus;
import com.revature.models.User;
import io.javalin.http.Handler;

//public class TicketStatusController {
//
//    //NOT WORKING ATM
//    TicketStatusDAO tsDAO = new TicketStatusDAO();
//
//    public Handler updateTicketStatusHandler = (ctx) -> {
//
//            String body = ctx.body();
//
//            Gson gson = new Gson();
//
//
//            User use = gson.fromJson(body, TicketStatus.class);
//
//            use = tsDAO.updateTicketStatus(use);
//
//
//            if(use != null){
//                ctx.status(201);
//                ctx.result(body);
//            } else {
//                ctx.status(406);
//                ctx.result("Employee username is already taken. Please create unique username.");
//            }
//
//    };


//}
