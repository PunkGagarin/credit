package com.gagarin.credit.controller;

import com.gagarin.credit.model.UserEntity;
import com.gagarin.credit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_up")
    public String getSignUp(Model model){
        model.addAttribute("user", new UserEntity());
        return "/auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute @Valid UserEntity user, BindingResult result){
        //TODO: Валидация
        userService.addUser(user);
        return "redirect:/orders";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model){
        if(Boolean.TRUE.equals(error))
            model.addAttribute("error", error);
        return "auth/sign_in";
    }

}
