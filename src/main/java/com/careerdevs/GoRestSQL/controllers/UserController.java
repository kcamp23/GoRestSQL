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
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.sun.tools.jdi.Packet.uID;

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

      try{
         long totalUsers = userRepo.count();
         userRepo.deleteAll();

         return new ResponseEntity<>("users Deeleted:" + totalUsers, HttpStatus.OK);

      }catch (HttpClientErrorException e){
         return  ApiErrorHandeling.customApiError(e.getMessage(), e.getStatusCode());

      }catch (Exception e) {
         return ApiErrorHandeling.genericApiError(e);

      }
      }

      @PostMapping("/upload/{id}")
   public ResponseEntity<?> uploadUserById (
          @PathVariable ("id") String userId,
          RestTemplate restTemplate
      ){
      try{
         if (ApiErrorHandeling.isStrNaN(userId)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, userId + " is not a valid ID");

         }
         int uID = Integer.parseInt(userId);

         String url  = "https://gorest.co.in/public/v2/users/" + uID;
         User foundUser = restTemplate.getForObject(url, User.class);

         if(foundUser == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User data was null");

         }
         User savedUser = userRepo.save(foundUser);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

      }catch (HttpClientErrorException e){
         return ApiErrorHandeling.customApiError(e.getMessage(),e.getStatusCode());

      }catch (Exception e){
         return ApiErrorHandeling.genericApiError(e);

      }
      }
      @PostMapping ("/")
   public ResponseEntity<?> createNewUSer (@RequestBody User newUser){
      try{
         User savedUser = userRepo.save(newUser);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);


      }catch (HttpClientErrorException e){
         return  ApiErrorHandeling.customApiError(e.getMessage(), e.getStatusCode());

      }catch (Exception e){
         return  ApiErrorHandeling.genericApiError(e);
      }
      }

      @PostMapping ("/")
   public ResponseEntity<?> updateUser (@RequestBody User updateUser);

   try{

      User savedUser = userRepo.save(updateUser);

      return new ResponseEntity<>(savedUser, HttpStatus.OK);
      
   }
}
