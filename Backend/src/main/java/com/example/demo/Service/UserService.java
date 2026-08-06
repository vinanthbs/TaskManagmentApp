package com.example.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Exception.InvalidCredentialsException;
import com.example.demo.Exception.UserNotFoundExeption;
import com.example.demo.Repository.UserRepo;
import com.example.demo.dto.UserResponseBody;

@Service
public class UserService {

    public final UserRepo UserRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService JwtService;


    public UserService(UserRepo UserRepo, PasswordEncoder passwordEncoder, JwtService JwtService){
        this.UserRepo = UserRepo;
        this.passwordEncoder=passwordEncoder;
        this.JwtService = JwtService;
    }

    public UserResponseBody registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUsername(user.getUsername());
        // newUser.setPassword(user.getPassword());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepo.save(newUser);
        UserResponseBody urb = new UserResponseBody();
        urb.setId(newUser.getId());
        urb.setUsername(newUser.getUsername());
        return urb;
    }

    public String login(User user) {
        System.out.println(user.getUsername());
        User currentUser = UserRepo.findByUsername(user.getUsername()).orElseThrow(()->new UserNotFoundExeption("User not found with name: "+ user.getUsername()));
        boolean matches = passwordEncoder.matches(user.getPassword(), currentUser.getPassword());
        if (matches) {
            return JwtService.generateToken(user.getUsername());
        } else {
            throw new InvalidCredentialsException("Invalid password");
        }
    }
}
