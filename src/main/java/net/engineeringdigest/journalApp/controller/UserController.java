package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<?> GetAll(){
        List<User> all = userServices.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/username/{MyUsername}")
    public ResponseEntity<?> GetUserByUserName(@PathVariable String MyUsername){
        User findusername= userServices.findByUsername(MyUsername);
        if(findusername != null){
            return new ResponseEntity<>(findusername,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> DeleteUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userServices.findByUsername(userName);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userServices.SaveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
