package com.hafsa.medischedule.controllers;


import com.hafsa.medischedule.model.User;
import com.hafsa.medischedule.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")

    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<User>> getAllDoctors() {
        List<User> doctors = userService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userService.getUserByEmail(user.getEmail());
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }



    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long userid)
    {
        Optional<com.hafsa.medischedule.model.User> user = userService.getUserById(userid);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userid)
    {
        userService.deleteUser(userid);
        return new ResponseEntity<String>("user deleted successfully",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user)
    {
        user.setId(id);
        User userUpdate = userService.updateUser(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

}
