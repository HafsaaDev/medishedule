package com.hafsa.medischedule.services;

import com.hafsa.medischedule.model.User;
import com.hafsa.medischedule.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getDoctors() {
        return userRepository.findByRole("doctor");
    }

    public User updateUser(User user) {
        return userRepository.findById(user.getId()).map(existingUser -> {
            existingUser.setFullname(user.getFullname());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }).orElse(null);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllDoctors() {
        return userRepository.findByRole("doctor");
    }

}
