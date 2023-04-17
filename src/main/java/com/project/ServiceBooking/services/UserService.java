package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Language;
import com.project.ServiceBooking.data.Role;
import com.project.ServiceBooking.data.Status;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.ServiceBooking.data.Status;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User findByEnterEmail(String enterEmail) {
        return userRepository.findByEnterEmail(enterEmail);
    }
    public User saveClient(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.CLIENT);
        user.setStatus(Status.INACTIVE);
       //user.setStatus(Status.ACTIVE);
        return saveUser(user);
    }
    public User saveSpecialist(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.SPECIALIST);
        user.setStatus(Status.INACTIVE);
        //user.setStatus(Status.ACTIVE);
        return saveUser(user);
    }

    public Set<Language> getLanguages(User user){
        Set<Language> languages = user.getLanguages();
        return languages;
    }
    public User getUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
}
