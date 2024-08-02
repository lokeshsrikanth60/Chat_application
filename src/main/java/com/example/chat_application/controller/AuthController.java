package com.example.chat_application.controller;
import com.example.chat_application.model.User;
import com.example.chat_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "registration";
    }
    @PostMapping("/register")
    public String registerUser(String username, String password, Model model) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("message", "Username already taken");
            return "registration";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        model.addAttribute("message", "User registered successfully");
        return "login";
    }
}