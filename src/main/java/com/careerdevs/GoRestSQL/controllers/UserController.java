package com.careerdevs.GoRestSQL.controllers;

import com.careerdevs.GoRestSQL.models.User;
import com.careerdevs.GoRestSQL.repos.UserRepo;
import com.careerdevs.GoRestSQL.util.ApiErrorHandeling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

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
    private UserRepo userRepo;

   @GetMapping ("/{id}")
   public ResponseEntity<?> getUserById (@PathVariable ("id") String id){
      try{
         if(ApiErrorHandeling.isStrNaN(id)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, id + "is not a valid ID");
         }
         int uID = Integer.parseInt(id);
         Optional<User> foundUser = userRepo.findById(uID);

         if  (foundUser.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "user not found with ID:" + id);
         }

         return new ResponseEntity<>(foundUser, HttpStatus.OK);

      }catch (HttpClientErrorException e){
         return ApiErrorHandeling.customApiError(e.getMessage()); e.getStatusCode());


      }catch (Exception e){
         return ApiErrorHandeling.genericApiError(e);

      }
   }
   @DeleteMapping ("/{id}")

   public ResponseEntity<?> deleteUserById (@PathVariable ("id") String id){

      try{

         if(ApiErrorHandeling.isStrNaN(id)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, id + "is not a valid id");
                     }
         int uId = Integer.parseInt(id);

         Optional<User> foundUser = userRepo.findById(uId);

         if (foundUser.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,  "User not found with ID:" + id);

         }


      userRepo.deleteById(uID);
         return new ResponseEntity<>(foundUser, HttpStatus.OK);

   }catch (HttpClientErrorException e){
         return  ApiErrorHandeling.customApiError(e.getMessage(), e.getStatusCode());

      }catch (Exception e) {
         return ApiErrorHandeling.genericApiError(e);
      }
      }
      @DeleteMapping ("/deleteall")
   public ResponseEntity<?> deleteAllUsers (){

      
      }

}
