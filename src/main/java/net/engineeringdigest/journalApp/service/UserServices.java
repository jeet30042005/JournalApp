package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public  void SaveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
        catch (Exception e){
            log.info("hahahhaah");
        }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void DeleteById(ObjectId myId){
        userRepository.deleteById(myId);
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId myId){
        return userRepository.findById(myId);
    }

    public void saveEntries(User oldEntry) {
        userRepository.save(oldEntry);
    }

    public User findByUserName(String userName) {
        return userRepository.getByUsername(userName);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
