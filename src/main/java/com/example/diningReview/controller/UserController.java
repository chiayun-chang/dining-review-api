package com.example.diningReview.controller;

import com.example.diningReview.model.*;
import com.example.diningReview.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByUserName(user.getUserName());
        if(userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user name already taken, try another name");
        }
        return userRepository.save(user);
    }

    @PutMapping("/user/{userName}")
    public User updateUserProfile(@PathVariable("userName") String name, @RequestBody User user) {
        Optional<User> userToUpdateOptional = this.userRepository.findByUserName(name);
        if(userToUpdateOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user name does not exist");
        }
        User userToUpdate = userToUpdateOptional.get();
        if(user.getCity() != null) {
            userToUpdate.setCity(user.getCity());
        }
        if(user.getState() != null) {
            userToUpdate.setState(user.getState());
        }
        if(user.getZipcode() != null) {
            userToUpdate.setZipcode(user.getZipcode());
        }
        if(user.getHasPeanutAllergy() != null) {
            userToUpdate.setHasPeanutAllergy(user.getHasPeanutAllergy());
        }
        if(user.getHasEggAllergy() != null) {
            userToUpdate.setHasEggAllergy(user.getHasEggAllergy());
        }
        if(user.getHasDairyAllergy() != null) {
            userToUpdate.setHasDairyAllergy(user.getHasDairyAllergy());
        }
        return userRepository.save(userToUpdate);
    }

    @GetMapping("/user/search")
    public Optional<User> getUserByUserName(@RequestParam(name="userName", required = false) String userName) {
        return userRepository.findByUserName(userName);
    }
}
