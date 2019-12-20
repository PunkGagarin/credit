package com.sberbank.credit.controller;

import com.sberbank.credit.model.UserEntity;
import com.sberbank.credit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    public static final String SIGN_UP_ENDPOINT = "/sign_up";

    public static final String SIGN_UP_VIEW = "auth/sign_up";

    public static final String LOGIN_ENDPOINT = "/login";

    public static final String LOGIN_VIEW = "auth/sign_in";


    @Autowired
    private UserService userService;

    private static UserEntity currentUser;

    @GetMapping(SIGN_UP_ENDPOINT)
    public String getSignUp(Model model) {
        model.addAttribute("user", new UserEntity());
        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_ENDPOINT)
    public String signUp(@ModelAttribute @Valid UserEntity user, BindingResult result) {
        //TODO: Валидация
        userService.addUser(user);
        return "redirect:"+LOGIN_ENDPOINT;
    }

    @GetMapping(LOGIN_ENDPOINT)
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        @RequestParam(name = "orderId", required = false) Long orderId, Model model) {
        if (Boolean.TRUE.equals(error))
            model.addAttribute("error", error);

        if (orderId != null)
            model.addAttribute("orderId", orderId);

        return LOGIN_VIEW;
    }

    @PostMapping(LOGIN_ENDPOINT)
    public String loginProcess(@RequestParam(name = "login", required = false) String login,
                               @RequestParam(name = "password", required = false) String password,
                               @RequestParam(name = "orderId", required = false) Long orderId) {

        UserEntity user = null;
        try {
            user = userService.authenticate(login, password);
            currentUser = user;
            return "redirect:/orders?orderId=" + orderId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login?error=true&orderId=" + orderId;
    }

    public static UserEntity getCurrentUser() {
        return currentUser;
    }
}
