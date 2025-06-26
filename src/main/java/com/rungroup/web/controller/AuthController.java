package com.rungroup.web.controller;

import com.rungroup.web.dto.RegistrationDto;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
                           BindingResult bindingResult, Model model) {
        System.out.println("register");
        System.out.println("registrationDto: " + registrationDto.toString());
        UserEntity existingUsersByEmail = userService.findByEmail(registrationDto.getEmail());
        if (existingUsersByEmail != null
                && existingUsersByEmail.getEmail() != null
                && !existingUsersByEmail.getEmail().isEmpty()) {
            System.out.println("register");
            System.out.println("email exists");
            return "redirect:/register?fail";
        }
        UserEntity existingUsersByUsername = userService.findByUsername(registrationDto.getUsername());
        if (existingUsersByUsername != null
                && existingUsersByUsername.getUsername() != null
                && !existingUsersByUsername.getUsername().isEmpty()) {
            System.out.println("register");
            System.out.println("username exists");
            return "redirect:/register?fail";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationDto", registrationDto);
            System.out.println("register");
            System.out.println("error");
            return "register";
        }
        userService.saveUser(registrationDto);
        System.out.println("register");
        System.out.println("user saved");
        return "redirect:/clubs?success";
    }
}
