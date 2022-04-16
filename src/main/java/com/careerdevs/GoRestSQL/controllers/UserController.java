package com.careerdevs.GoRestSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {

    /* GET route that returns one user by ID from the SQL database
    GET route that returns all users stored in the SQL database
    DELETE route that deletes one user by ID from SQL database
    DELETE  route that deletes all users from SQL database
    POST route the queries one user by ID from GoREST and saves their data to the local database
    POST route that uplaods all users from the GoREST API into the SQL database
    POST route that creates a user on JUST the SQL database
    PUT route that updates a user on JUST the SQL database
     */

   @Autowired
    


}
