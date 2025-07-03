package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/health-check")
    public String healthCheck(){

        return "OK";
    }
    @PostMapping("/create-entries")
    public ResponseEntity<?> CreateUser(@RequestBody User user){
        try {
            userServices.SaveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
