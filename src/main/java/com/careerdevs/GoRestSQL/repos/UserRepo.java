package com.careerdevs.GoRestSQL.repos;


import com.careerdevs.GoRestSQL.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer>{


}
